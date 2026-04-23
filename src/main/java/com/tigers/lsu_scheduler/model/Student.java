package com.tigers.lsu_scheduler.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private String year;
    private String concentration;
    private String email;
    private String studentId;
    private int creditsCompleted;

    @ElementCollection
    @CollectionTable(name = "student_completed_courses",
                     joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "course_id")
    private List<String> completedCourses;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public int getCreditsCompleted() { return creditsCompleted; }
    public void setCreditsCompleted(int c) { this.creditsCompleted = c; }

    public List<String> getCompletedCourses() { return completedCourses; }
    public void setCompletedCourses(List<String> c) { this.completedCourses = c; }

    public String getConcentration() { return concentration; }
    public void setConcentration(String concentration) { this.concentration = concentration; }
}