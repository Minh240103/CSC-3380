package com.tigers.lsu_scheduler.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String courseId;

    private String courseName;
    private String description;
    private int creditHours;
    private String type;
    private String semesterOffered;
    private boolean required;

    @ElementCollection
    @CollectionTable(name = "course_prerequisites",
                     joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "prerequisite_id")
    private List<String> prerequisites;

    @Transient
    private boolean eligible = true;

    @Transient
    private String priority = "MEDIUM";

    @Transient
    private int sectionCount = 1;

    @Transient
    private String timeSlot = "TBD";

    @Transient
    private String building = "TBD";

    @Transient
    private String missingPrereq = "";

    @Transient
    private String semesterOnly = null;

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCreditHours() { return creditHours; }
    public void setCreditHours(int creditHours) { this.creditHours = creditHours; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSemesterOffered() { return semesterOffered; }
    public void setSemesterOffered(String s) { this.semesterOffered = s; }

    public boolean isRequired() { return required; }
    public void setRequired(boolean required) { this.required = required; }

    public List<String> getPrerequisites() { return prerequisites; }
    public void setPrerequisites(List<String> p) { this.prerequisites = p; }

    public boolean isEligible() { return eligible; }
    public void setEligible(boolean eligible) { this.eligible = eligible; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public int getSectionCount() { return sectionCount; }
    public void setSectionCount(int sectionCount) { this.sectionCount = sectionCount; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }

    public String getMissingPrereq() { return missingPrereq; }
    public void setMissingPrereq(String missingPrereq) { this.missingPrereq = missingPrereq; }

    public String getSemesterOnly() { return semesterOnly; }
    public void setSemesterOnly(String semesterOnly) { this.semesterOnly = semesterOnly; }
}