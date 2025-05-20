package com.SDP.project.Repository;

import com.SDP.project.models.PaperNumbering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaperNumberingRepository extends JpaRepository<PaperNumbering, Integer> {
    boolean existsPaperNumberingByGradeAndCategory(String grade, String category );

    PaperNumbering getPaperNumberingByGradeAndCategory(String grade, String category);

    Optional<PaperNumbering> findByGradeAndCategory(String grade, String category);
}
