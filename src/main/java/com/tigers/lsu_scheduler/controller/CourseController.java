package com.tigers.lsu_scheduler.controller;

import com.tigers.lsu_scheduler.model.Course;
import com.tigers.lsu_scheduler.model.Student;
import com.tigers.lsu_scheduler.repository.CourseRepository;
import com.tigers.lsu_scheduler.repository.StudentRepository;
import com.tigers.lsu_scheduler.service.EligibilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EligibilityService eligibilityService;

    @GetMapping("/courses")
    public String courses(HttpSession session,
                          Model model,
                          @RequestParam(required = false) String q,
                          @RequestParam(required = false) String type,
                          @RequestParam(required = false) String semester,
                          @RequestParam(required = false) Boolean eligibleOnly) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        // Reload student fresh from database
        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        // Load and check eligibility for all courses
        List<String> completedCourses = student.getCompletedCourses() != null
            ? student.getCompletedCourses() : List.of();

        List<Course> courses = courseRepository.findAll().stream()
            .filter(c -> !completedCourses.contains(c.getCourseId()))
            .collect(java.util.stream.Collectors.toList());

courses = eligibilityService.checkEligibility(courses, student);

        // Apply search filter
        if (q != null && !q.trim().isEmpty()) {
            String search = q.trim().toLowerCase();
            courses = courses.stream()
                .filter(c -> c.getCourseId().toLowerCase().contains(search)
                          || c.getCourseName().toLowerCase().contains(search))
                .collect(Collectors.toList());
        }

        // Apply type filter
        if (type != null && !type.isEmpty()) {
            courses = courses.stream()
                .filter(c -> type.equals(c.getType()))
                .collect(Collectors.toList());
        }

        // Apply semester filter
        if (semester != null && !semester.isEmpty()) {
            courses = courses.stream()
                .filter(c -> semester.equals(c.getSemesterOffered())
                          || "BOTH".equals(c.getSemesterOffered()))
                .collect(Collectors.toList());
        }

        // Apply eligible only filter
        if (eligibleOnly != null && eligibleOnly) {
            courses = courses.stream()
                .filter(Course::isEligible)
                .collect(Collectors.toList());
        }

        model.addAttribute("title", "Course Catalog");
        model.addAttribute("activePage", "courses");
        model.addAttribute("student", student);
        model.addAttribute("currentSemester", "Spring 2026");
        model.addAttribute("registrationOpen", true);
        model.addAttribute("courses", courses);
        model.addAttribute("searchQuery", q);
        model.addAttribute("filterType", type);
        model.addAttribute("filterSemester", semester);
        model.addAttribute("eligibleOnly", eligibleOnly != null && eligibleOnly);

        return "courses";
    }
}