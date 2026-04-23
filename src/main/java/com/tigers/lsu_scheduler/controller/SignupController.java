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
public class SignupController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String studentId,
                           @RequestParam String concentration,
                           HttpSession session,
                           Model model) {

        // Check if email already exists
        Student existing = studentRepository.findByEmail(email);
        if (existing != null) {
            model.addAttribute("error", "An account with that email already exists. Please sign in.");
            return "signup";
        }

        // Check if student ID already exists
        Student existingId = studentRepository.findByStudentId(studentId);
        if (existingId != null) {
            model.addAttribute("error", "An account with that student ID already exists.");
            return "signup";
        }

        // Create and save the new student
        Student newStudent = new Student();
        newStudent.setName(firstName + " " + lastName);
        newStudent.setEmail(email);
        newStudent.setStudentId(studentId);
        newStudent.setMajor("Computer Science");
        newStudent.setYear("Freshman");
        newStudent.setConcentration(concentration);
        newStudent.setCreditsCompleted(0);

        studentRepository.save(newStudent);

        // Log them in automatically
        session.setAttribute("loggedInStudent", newStudent);

        return "redirect:/";
    }
}