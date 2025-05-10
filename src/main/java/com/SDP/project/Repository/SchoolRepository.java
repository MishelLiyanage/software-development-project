package com.SDP.project.Repository;

import com.SDP.project.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    Optional<School> findByEmail(String email);

    Optional<School> findByAccountId(int id);

    Optional<School> findById(int id);

    @Query(value = "SELECT COUNT(*) FROM school", nativeQuery = true)
    int countRegisteredSchools();
}
