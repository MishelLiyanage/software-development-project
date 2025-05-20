package com.SDP.project.Repository;

import com.SDP.project.models.ModelPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelPaperRepository extends JpaRepository<ModelPaper, Integer> {

    @Query("SELECT COUNT(m) > 0 FROM ModelPaper m WHERE m.grade = :grade AND m.category = :category AND m.paperNo = :paperNo AND m.partNo = :partNo")
    boolean existsModelPaper(@Param("grade") String grade, @Param("category") String category, @Param("paperNo") String paperNo, @Param("partNo") String partNo);

    Optional<ModelPaper> findByGradeAndCategoryAndPaperNoAndPartNo(String grade, String category, String paperNo, String partNo);
}

