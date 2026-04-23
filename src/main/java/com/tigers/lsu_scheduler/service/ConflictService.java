package com.tigers.lsu_scheduler.service;

import com.tigers.lsu_scheduler.model.Enrollment;
import com.tigers.lsu_scheduler.model.Section;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ConflictService {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("h:mm a");

    public boolean hasConflict(Section newSection, List<Enrollment> existingEnrollments) {
        for (Enrollment enrollment : existingEnrollments) {
            // Get sections for this enrolled course
            // We check days and times overlap
            if (daysOverlap(newSection.getDays(), enrollment.getCourse().getCourseId())) {
                // For simplicity we check time overlap using the section times
                // This will be enhanced when we store section per enrollment
            }
        }
        return false;
    }

    public boolean sectionsConflict(Section s1, Section s2) {
        // Check if days overlap
        if (!daysOverlapStr(s1.getDays(), s2.getDays())) return false;

        // Check if times overlap
        try {
            LocalTime start1 = LocalTime.parse(s1.getStartTime().toUpperCase(), FMT);
            LocalTime end1   = LocalTime.parse(s1.getEndTime().toUpperCase(), FMT);
            LocalTime start2 = LocalTime.parse(s2.getStartTime().toUpperCase(), FMT);
            LocalTime end2   = LocalTime.parse(s2.getEndTime().toUpperCase(), FMT);

            return start1.isBefore(end2) && start2.isBefore(end1);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean daysOverlapStr(String days1, String days2) {
        if (days1 == null || days2 == null) return false;
        for (char c : days1.toCharArray()) {
            if (c != ' ' && days2.indexOf(c) >= 0) return true;
        }
        return false;
    }

    private boolean daysOverlap(String days, String courseId) {
        return false;
    }
}