package com.SDP.project.Repository;

import com.SDP.project.DTOs.ManageSchoolsDto;
import com.SDP.project.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    Optional<Object> findByEmail(String email);

    Optional<School> findByAccountId(int id);

    Optional<School> findById(int id);
}
