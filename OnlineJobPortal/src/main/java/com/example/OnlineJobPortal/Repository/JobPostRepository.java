package com.example.OnlineJobPortal.Repository;

import com.example.OnlineJobPortal.Entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

}
