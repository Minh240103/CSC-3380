package com.tigers.lsu_scheduler.repository;

import com.tigers.lsu_scheduler.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Student findByEmailAndStudentId(String email, String studentId);
    Student findByStudentId(String studentId);
}