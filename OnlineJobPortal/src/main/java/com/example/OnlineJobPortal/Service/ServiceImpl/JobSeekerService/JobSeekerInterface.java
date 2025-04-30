package com.example.OnlineJobPortal.Service.ServiceImpl.JobSeekerService;



import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Model.VisibleJobPost;

import java.util.List;

public interface JobSeekerInterface {
    public List<VisibleJobPost> getAllJobsWithFilter(String skills, String city, String state, String jobType , int page, int size);
    public String applyToJob(User user,Long jobPostId);
}
