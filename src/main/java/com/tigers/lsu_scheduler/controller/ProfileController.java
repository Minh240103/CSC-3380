package com.tigers.lsu_scheduler.controller;

import com.tigers.lsu_scheduler.model.Student;
import com.tigers.lsu_scheduler.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model,
                          @RequestParam(required = false) String success) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        model.addAttribute("title", "My Profile");
        model.addAttribute("activePage", "profile");
        model.addAttribute("currentSemester", "Spring 2026");
        model.addAttribute("registrationOpen", true);
        model.addAttribute("student", student);
        model.addAttribute("successMsg", success != null ? "Profile updated successfully!" : null);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam String name,
                                @RequestParam(required = false, defaultValue = "Computer Science") String major,
                                @RequestParam String year,
                                @RequestParam String concentration,
                                HttpSession session) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        student.setName(name);
        student.setMajor(major);
        student.setYear(year);
        student.setConcentration(concentration);
        studentRepository.save(student);

        session.setAttribute("loggedInStudent", student);

        return "redirect:/profile?success=true";
    }
}