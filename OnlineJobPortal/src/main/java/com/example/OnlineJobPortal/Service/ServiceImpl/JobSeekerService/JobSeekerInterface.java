package com.example.OnlineJobPortal.Service.ServiceImpl.JobSeekerService;



import com.example.OnlineJobPortal.Model.VisibleJobPost;

import java.util.List;

public interface JobSeekerInterface {
    public List<VisibleJobPost> getAllJobPostFromDb();
}
