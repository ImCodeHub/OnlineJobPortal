package com.example.OnlineJobPortal.Service.ServiceImpl.JobSeekerService;



import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Model.VisibleJobPost;

import java.util.List;

public interface JobSeekerInterface {
    public List<VisibleJobPost> getAllJobPostFromDb();
    public String applyToJob(User user,Long jobPostId);
}
