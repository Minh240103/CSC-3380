package com.tigers.lsu_scheduler.repository;

import com.tigers.lsu_scheduler.model.DegreePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DegreePlanRepository extends JpaRepository<DegreePlan, Long> {
    List<DegreePlan> findByConcentrationOrderByRecommendedYearAscRecommendedSemesterAsc(String concentration);
}