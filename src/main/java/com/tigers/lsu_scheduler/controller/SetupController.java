package com.tigers.lsu_scheduler.controller;

import com.tigers.lsu_scheduler.model.Student;
import com.tigers.lsu_scheduler.repository.StudentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SetupController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/degree-plan/toggle")
    public String toggleCourse(@RequestParam String courseId,
                               HttpSession session) {

        Student sessionStudent = (Student) session.getAttribute("loggedInStudent");
        if (sessionStudent == null) return "redirect:/login";

        Student student = studentRepository.findById(sessionStudent.getId()).orElse(null);
        if (student == null) return "redirect:/login";

        List<String> completed = student.getCompletedCourses();

        if (completed != null && completed.contains(courseId)) {
            jdbcTemplate.update(
                "DELETE FROM student_completed_courses WHERE student_id = ? AND course_id = ?",
                student.getId(), courseId);
        } else {
            jdbcTemplate.update(
                "INSERT INTO student_completed_courses (student_id, course_id) VALUES (?, ?)",
                student.getId(), courseId);
        }

        return "redirect:/degree-plan";
    }
}
