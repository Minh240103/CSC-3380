package com.tigers.lsu_scheduler.controller;

import com.tigers.lsu_scheduler.model.DegreePlan;
import com.tigers.lsu_scheduler.model.Student;
import com.tigers.lsu_scheduler.repository.DegreePlanRepository;
import com.tigers.lsu_scheduler.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DegreePlanController {

    @Autowired
    private DegreePlanRepository degreePlanRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/degree-plan")
    public String degreePlan(HttpSession session, Model model) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        String concentration = student.getConcentration() != null
                ? student.getConcentration() : "Software Engineering";

        List<String> completed = student.getCompletedCourses() != null 
        ? student.getCompletedCourses() : List.of();
        final List<String> finalCompleted = completed;

        List<DegreePlan> allCourses = degreePlanRepository
                .findByConcentrationOrderByRecommendedYearAscRecommendedSemesterAsc(concentration);

        long completedCount = allCourses.stream()
                .filter(c -> finalCompleted.contains(c.getCourseId())).count();
        long remainingCount = allCourses.size() - completedCount;
        int totalCredits = allCourses.stream().mapToInt(DegreePlan::getCreditHours).sum();
        int completedCredits = allCourses.stream()
                .filter(c -> finalCompleted.contains(c.getCourseId()))
                .mapToInt(DegreePlan::getCreditHours).sum();
        int progressPct = totalCredits > 0 ? (completedCredits * 100 / totalCredits) : 0;

        
        model.addAttribute("title", "Degree Plan");
        model.addAttribute("activePage", "degree");
        model.addAttribute("currentSemester", "Spring 2026");
        model.addAttribute("registrationOpen", true);
        model.addAttribute("student", student);
        model.addAttribute("concentration", concentration);
        model.addAttribute("completed", finalCompleted);
        model.addAttribute("completedCount", completedCount);
        model.addAttribute("remainingCount", remainingCount);
        model.addAttribute("totalCredits", totalCredits);
        model.addAttribute("completedCredits", completedCredits);
        model.addAttribute("progressPct", progressPct);
        model.addAttribute("sem1", filterBySem(allCourses, 1, 1));
        model.addAttribute("sem2", filterBySem(allCourses, 2, 1));
        model.addAttribute("sem3", filterBySem(allCourses, 3, 2));
        model.addAttribute("sem4", filterBySem(allCourses, 4, 2));
        model.addAttribute("sem5", filterBySem(allCourses, 5, 3));
        model.addAttribute("sem6", filterBySem(allCourses, 6, 3));
        model.addAttribute("sem7", filterBySem(allCourses, 7, 4));
        model.addAttribute("sem8", filterBySem(allCourses, 8, 4));

        return "degree-plan";
    }

    private List<DegreePlan> filterBySem(List<DegreePlan> courses, int sem, int year) {
        return courses.stream()
                .filter(c -> c.getRecommendedSemester() == sem && c.getRecommendedYear() == year)
                .collect(Collectors.toList());
    }
}