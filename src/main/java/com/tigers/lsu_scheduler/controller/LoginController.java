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
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String studentId,
                          HttpSession session,
                          Model model) {

        Student student = studentRepository.findByEmailAndStudentId(email, studentId);

        if (student == null) {
            model.addAttribute("error", "No account found with that email and student ID. Please try again.");
            return "login";
        }

        session.setAttribute("loggedInStudent", student);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}