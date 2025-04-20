package com.SDP.project.Repository;

import com.SDP.project.models.PaperSets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PapersetRepository extends JpaRepository<PaperSets, Integer> {

    @Query("SELECT COUNT(m) > 0 FROM PaperSets m WHERE m.grade = :grade AND m.category = :category")
    boolean existsModelPaper(@Param("grade") String grade, @Param("category") String category);

    PaperSets findByGradeAndCategory(String grade, String category);
}
