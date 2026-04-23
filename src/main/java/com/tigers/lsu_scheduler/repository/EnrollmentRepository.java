package com.tigers.lsu_scheduler.repository;

import com.tigers.lsu_scheduler.model.Enrollment;
import com.tigers.lsu_scheduler.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    boolean existsByStudentAndCourse_CourseId(Student student, String courseId);
    void deleteByStudentAndCourse_CourseId(Student student, String courseId);
}
