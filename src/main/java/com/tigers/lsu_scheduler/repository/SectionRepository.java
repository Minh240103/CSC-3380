package com.tigers.lsu_scheduler.repository;

import com.tigers.lsu_scheduler.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByCourse_CourseId(String courseId);
}
