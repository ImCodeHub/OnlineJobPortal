package com.example.OnlineJobPortal.Service.ServiceImpl.HRService;

import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Model.JobPostByHrDto;
import com.example.OnlineJobPortal.Model.JobSeekerDto;

import java.util.List;

public interface HrServiceInterface {
    public String savePost(User user, JobPostByHrDto jobPostByHrDto);
    public List<JobPost> getYourPost(Long hrId);
    public List<JobSeekerDto> getApplicant(Long jobPostId);
}
