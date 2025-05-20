package com.SDP.project.Repository;

import com.SDP.project.models.PaperNumbering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaperNumberingRepository extends JpaRepository<PaperNumbering, Integer> {
    boolean existsPaperNumberingByGradeAndCategoryAndSequenceNo(String grade, String category, String sequenceNo);

    PaperNumbering getPaperNumberingByGradeAndCategoryAndSequenceNo(String grade, String category, String sequenceNo);

    Optional<PaperNumbering> findByGradeAndCategory(String grade, String category);
}
