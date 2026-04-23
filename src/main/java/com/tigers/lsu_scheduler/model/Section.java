package com.tigers.lsu_scheduler.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String instructor;
    private String days;
    private String startTime;
    private String endTime;
    private String building;
    private String room;
    private int enrolledCount;
    private int capacity;
    private String rmpLink;
    private String sectionNumber;

    public Long getSectionId() { return sectionId; }
    public void setSectionId(Long sectionId) { this.sectionId = sectionId; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getDays() { return days; }
    public void setDays(String days) { this.days = days; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public int getEnrolledCount() { return enrolledCount; }
    public void setEnrolledCount(int enrolledCount) { this.enrolledCount = enrolledCount; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getRmpLink() { return rmpLink; }
    public void setRmpLink(String rmpLink) { this.rmpLink = rmpLink; }

    public String getSectionNumber() { return sectionNumber; }
    public void setSectionNumber(String sectionNumber) { this.sectionNumber = sectionNumber; }
}
