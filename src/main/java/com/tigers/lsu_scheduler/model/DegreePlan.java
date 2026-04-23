package com.tigers.lsu_scheduler.model;

import jakarta.persistence.*;

@Entity
@Table(name = "degree_plan")
public class DegreePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseId;
    private String courseName;
    private int creditHours;
    private int recommendedSemester;
    private int recommendedYear;
    private String category;
    private String concentration;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCreditHours() { return creditHours; }
    public void setCreditHours(int creditHours) { this.creditHours = creditHours; }

    public int getRecommendedSemester() { return recommendedSemester; }
    public void setRecommendedSemester(int s) { this.recommendedSemester = s; }

    public int getRecommendedYear() { return recommendedYear; }
    public void setRecommendedYear(int y) { this.recommendedYear = y; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getConcentration() { return concentration; }
    public void setConcentration(String concentration) { this.concentration = concentration; }
}