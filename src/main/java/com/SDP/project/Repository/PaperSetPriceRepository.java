package com.SDP.project.Repository;

import com.SDP.project.models.PaperSetPrice;
import com.SDP.project.models.PaperSets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperSetPriceRepository extends JpaRepository<PaperSetPrice, Integer> {
}
