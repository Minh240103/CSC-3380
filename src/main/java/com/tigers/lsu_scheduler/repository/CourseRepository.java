package com.tigers.lsu_scheduler.repository;

import com.tigers.lsu_scheduler.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByType(String type);
    List<Course> findByRequired(boolean required);
}
