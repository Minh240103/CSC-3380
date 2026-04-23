package com.tigers.lsu_scheduler.service;

import com.tigers.lsu_scheduler.model.Course;
import com.tigers.lsu_scheduler.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityService {

    public List<Course> checkEligibility(List<Course> courses, Student student) {

        List<String> completed = student.getCompletedCourses();

        for (Course course : courses) {
            List<String> prereqs = course.getPrerequisites();

            if (prereqs == null || prereqs.isEmpty()) {
                course.setEligible(true);
                course.setPriority("LOW");
            } else if (completed != null && completed.containsAll(prereqs)) {
                course.setEligible(true);
                if (course.isRequired()) {
                    course.setPriority("HIGH");
                } else {
                    course.setPriority("MEDIUM");
                }
            } else {
                course.setEligible(false);
                course.setPriority("LOW");
                if (prereqs != null && completed != null) {
                    for (String prereq : prereqs) {
                        if (!completed.contains(prereq)) {
                            course.setMissingPrereq(prereq);
                            break;
                        }
                    }
                }
            }
        }

        // Sort: eligible first, then required on top
        List<Course> mutableCourses = new java.util.ArrayList<>(courses);
        mutableCourses.sort((a, b) -> {
            if (a.isEligible() && !b.isEligible()) return -1;
            if (!a.isEligible() && b.isEligible()) return 1;
            if (a.isRequired() && !b.isRequired()) return -1;
            if (!a.isRequired() && b.isRequired()) return 1;
            return 0;
        });
            return mutableCourses;

        
    }
}