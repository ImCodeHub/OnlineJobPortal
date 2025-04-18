package com.example.OnlineJobPortal.Repository;

import com.example.OnlineJobPortal.Entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    Optional<Qualification> findByUserId(Long userId);
}
