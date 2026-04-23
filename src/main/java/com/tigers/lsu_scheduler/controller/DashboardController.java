package com.tigers.lsu_scheduler.controller;

import com.tigers.lsu_scheduler.model.Course;
import com.tigers.lsu_scheduler.model.DegreePlan;
import com.tigers.lsu_scheduler.model.Section;
import com.tigers.lsu_scheduler.model.Student;
import com.tigers.lsu_scheduler.repository.CourseRepository;
import com.tigers.lsu_scheduler.repository.DegreePlanRepository;
import com.tigers.lsu_scheduler.repository.EnrollmentRepository;
import com.tigers.lsu_scheduler.repository.SectionRepository;
import com.tigers.lsu_scheduler.repository.StudentRepository;
import com.tigers.lsu_scheduler.service.EligibilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EligibilityService eligibilityService;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private DegreePlanRepository degreePlanRepository;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(HttpSession session, Model model) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        List<String> completedCourses = student.getCompletedCourses() != null
                ? student.getCompletedCourses() : List.of();

        // Filter out completed courses
        List<Course> courses = courseRepository.findAll().stream()
                .filter(c -> !completedCourses.contains(c.getCourseId()))
                .collect(Collectors.toList());

        courses = eligibilityService.checkEligibility(courses, student);

        // Populate section info for each course
        for (Course course : courses) {
            List<Section> sections = sectionRepository
                    .findByCourse_CourseId(course.getCourseId());
            course.setSectionCount(sections.size());
            if (!sections.isEmpty()) {
                Section first = sections.get(0);
                course.setTimeSlot(first.getDays() + " " +
                        first.getStartTime() + " – " + first.getEndTime());
                course.setBuilding(first.getBuilding());
            }
        }

        // Get enrollments for sidebar
        var enrollments = enrollmentRepository.findByStudent(student);
        int totalCredits = enrollments.stream()
                .mapToInt(e -> e.getCourse().getCreditHours()).sum();

        long eligibleCount = courses.stream().filter(Course::isEligible).count();
        long requiredCount = courses.stream()
                .filter(c -> c.isEligible() && c.isRequired()).count();

        // Build degree progress bars
        String concentration = student.getConcentration() != null
                ? student.getConcentration() : "Software Engineering";

        List<DegreePlan> degreePlan = degreePlanRepository
                .findByConcentrationOrderByRecommendedYearAscRecommendedSemesterAsc(concentration);

        // Group by category and calculate progress
        Map<String, List<DegreePlan>> byCategory = degreePlan.stream()
                .collect(Collectors.groupingBy(DegreePlan::getCategory));

        List<Map<String, Object>> degreeProgress = new ArrayList<>();
        for (Map.Entry<String, List<DegreePlan>> entry : byCategory.entrySet()) {
            String category = entry.getKey();
            List<DegreePlan> categoryCourses = entry.getValue();
            int total = categoryCourses.stream()
                    .mapToInt(DegreePlan::getCreditHours).sum();
            int completed = categoryCourses.stream()
                    .filter(c -> completedCourses.contains(c.getCourseId()))
                    .mapToInt(DegreePlan::getCreditHours).sum();
            int pct = total > 0 ? (completed * 100 / total) : 0;

            degreeProgress.add(Map.of(
                    "label", category,
                    "completed", completed,
                    "total", total,
                    "pct", pct
            ));
        }

        model.addAttribute("title", "Dashboard");
        model.addAttribute("activePage", "dashboard");
        model.addAttribute("currentSemester", "Spring 2026");
        model.addAttribute("registrationOpen", true);
        model.addAttribute("student", student);
        model.addAttribute("eligibleCount", eligibleCount);
        model.addAttribute("requiredThisSem", requiredCount);
        List<DegreePlan> studentPlan = degreePlanRepository
            .findByConcentrationOrderByRecommendedYearAscRecommendedSemesterAsc(concentration);

        int realCreditsCompleted = studentPlan.stream()
            .filter(c -> completedCourses.contains(c.getCourseId()))
            .mapToInt(DegreePlan::getCreditHours)
            .sum();

        model.addAttribute("creditsCompleted", realCreditsCompleted);
        model.addAttribute("creditsRemaining", 120 - realCreditsCompleted);
        model.addAttribute("eligibleCourses", courses);
        model.addAttribute("degreeProgress", degreeProgress);
        model.addAttribute("selectedSections", enrollments);
        model.addAttribute("alerts", List.of());
        model.addAttribute("totalCredits", totalCredits);

        return "dashboard";
    }
}