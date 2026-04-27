package com.tigers.lsu_scheduler.controller;

import com.tigers.lsu_scheduler.model.Course;
import com.tigers.lsu_scheduler.model.Enrollment;
import com.tigers.lsu_scheduler.model.Section;
import com.tigers.lsu_scheduler.model.Student;
import com.tigers.lsu_scheduler.repository.CourseRepository;
import com.tigers.lsu_scheduler.repository.EnrollmentRepository;
import com.tigers.lsu_scheduler.repository.SectionRepository;
import com.tigers.lsu_scheduler.repository.StudentRepository;
import com.tigers.lsu_scheduler.service.ConflictService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ConflictService conflictService;

    @GetMapping("/schedule")
    public String schedule(HttpSession session, Model model) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);

        int totalCredits = enrollments.stream()
                .mapToInt(e -> e.getCourse().getCreditHours())
                .sum();

        model.addAttribute("title", "My Schedule");
        model.addAttribute("activePage", "schedule");
        model.addAttribute("currentSemester", "Spring 2026");
        model.addAttribute("registrationOpen", true);
        model.addAttribute("student", student);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("totalCredits", totalCredits);

        List<com.tigers.lsu_scheduler.model.Section> sections = new java.util.ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            sections.addAll(sectionRepository.findByCourse_CourseId(
            enrollment.getCourse().getCourseId()));
}
model.addAttribute("sections", sections);

        return "schedule";
    }

    @PostMapping("/schedule/add")
    public String addCourse(@RequestParam String courseId,
                            @RequestParam(required = false) Long sectionId,
                            HttpSession session,
                            RedirectAttributes redirectAttrs) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        // Check if already enrolled
        if (enrollmentRepository.existsByStudentAndCourse_CourseId(student, courseId)) {
            redirectAttrs.addFlashAttribute("errorMsg", "You are already enrolled in " + courseId);
            return "redirect:/schedule";
        }

        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) return "redirect:/dashboard";

        // Check for time conflicts if sectionId is provided
        if (sectionId != null) {
            Section newSection = sectionRepository.findById(sectionId).orElse(null);
            if (newSection != null) {
                List<Enrollment> existing = enrollmentRepository.findByStudent(student);
                for (Enrollment enrollment : existing) {
                    if (enrollment.getSectionId() != null) {
                        Section existingSection = sectionRepository
                                .findById(enrollment.getSectionId()).orElse(null);
                        if (existingSection != null &&
                                conflictService.sectionsConflict(newSection, existingSection)) {
                            redirectAttrs.addFlashAttribute("errorMsg",
                                    "Time conflict with " + enrollment.getCourse().getCourseId()
                                    + " (" + existingSection.getDays() + " "
                                    + existingSection.getStartTime() + ")");
                            return "redirect:/courses/" + courseId;
                        }
                    }
                }
            }
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setSectionId(sectionId);
        enrollmentRepository.save(enrollment);

        redirectAttrs.addFlashAttribute("successMsg", courseId + " added to your schedule!");
        return "redirect:/schedule";
    }

    @PostMapping("/schedule/remove")
    @Transactional
    public String removeCourse(@RequestParam String courseId,
                               HttpSession session) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        enrollmentRepository.deleteByStudentAndCourse_CourseId(student, courseId);
        return "redirect:/schedule";
    }
}