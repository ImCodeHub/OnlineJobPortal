package com.example.OnlineJobPortal.Service.ServiceImpl.HRService;

import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.User;

public interface HrServiceInterface {
    public String savePost(User user, JobPost jobPost);
}
