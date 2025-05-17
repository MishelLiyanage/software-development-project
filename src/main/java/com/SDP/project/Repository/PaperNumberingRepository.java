package com.SDP.project.Repository;

import com.SDP.project.models.PaperNumbering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperNumberingRepository extends JpaRepository<PaperNumbering, Integer> {
    boolean existsPaperNumberingByGradeAndCategory(String grade, String category);

    PaperNumbering getPaperNumberingByGradeAndCategory(String grade, String category);
}
