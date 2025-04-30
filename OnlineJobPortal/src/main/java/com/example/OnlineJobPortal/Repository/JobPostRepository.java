package com.example.OnlineJobPortal.Repository;

import com.example.OnlineJobPortal.Entity.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    @Query("SELECT j FROM JobPost j WHERE" +
            "(:skills IS NULL OR j.requiredSkills LIKE %:skills%) AND " +
            "(:city IS NULL OR j.city LIKE %:city%) AND " +
            "(:state IS NULL OR j.state LIKE %:state%) AND " +
            "(:jobType IS NULL OR j.jobType LIKE %:jobType%)"
    )

    public Page<JobPost> findAllWithFilters(@Param("skills") String skills,
                                            @Param("city") String city,
                                            @Param("state") String state,
                                            @Param("jobType") String jobType,
                                            Pageable pageable);
}
