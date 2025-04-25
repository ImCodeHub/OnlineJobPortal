package com.example.OnlineJobPortal.Repository;

import com.example.OnlineJobPortal.Entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    public List<JobApplication> findByJobPostId(Long jobPostId);

}
