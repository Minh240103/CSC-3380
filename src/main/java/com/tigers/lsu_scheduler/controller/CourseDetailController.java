package com.tigers.lsu_scheduler.controller;

import com.tigers.lsu_scheduler.model.Course;
import com.tigers.lsu_scheduler.model.Enrollment;
import com.tigers.lsu_scheduler.model.Section;
import com.tigers.lsu_scheduler.model.Student;
import com.tigers.lsu_scheduler.repository.CourseRepository;
import com.tigers.lsu_scheduler.repository.EnrollmentRepository;
import com.tigers.lsu_scheduler.repository.SectionRepository;
import com.tigers.lsu_scheduler.repository.StudentRepository;
import com.tigers.lsu_scheduler.service.EligibilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CourseDetailController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EligibilityService eligibilityService;

    @GetMapping("/courses/{courseId}")
    public String courseDetail(@PathVariable String courseId,
                               HttpSession session,
                               Model model) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        Course course = courseRepository.findById(courseId).orElse(null);
        if (course == null) return "redirect:/courses";

        eligibilityService.checkEligibility(List.of(course), student);

        List<Section> sections = sectionRepository.findByCourse_CourseId(courseId);

        boolean alreadyEnrolled = enrollmentRepository
                .existsByStudentAndCourse_CourseId(student, courseId);

        // Find which specific section was enrolled
        Long enrolledSectionId = null;
        if (alreadyEnrolled) {
            List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
            for (Enrollment e : enrollments) {
                if (e.getCourse().getCourseId().equals(courseId)) {
                    enrolledSectionId = e.getSectionId();
                    break;
                }
            }
        }

        model.addAttribute("title", course.getCourseId() + " — " + course.getCourseName());
        model.addAttribute("activePage", "courses");
        model.addAttribute("currentSemester", "Spring 2026");
        model.addAttribute("registrationOpen", true);
        model.addAttribute("student", student);
        model.addAttribute("course", course);
        model.addAttribute("sections", sections);
        model.addAttribute("alreadyEnrolled", alreadyEnrolled);
        model.addAttribute("enrolledSectionId", enrolledSectionId);

        return "course-detail";
    }
}