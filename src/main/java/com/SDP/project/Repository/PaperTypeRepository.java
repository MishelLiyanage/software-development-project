package com.SDP.project.Repository;

import com.SDP.project.models.PaperTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperTypeRepository extends JpaRepository<PaperTypes, Integer> {

    @Query("SELECT COUNT(p) > 0 FROM PaperTypes p WHERE p.paperType = :paperType")
    boolean existsByPaperType(@Param("paperType") String paperType);
}
