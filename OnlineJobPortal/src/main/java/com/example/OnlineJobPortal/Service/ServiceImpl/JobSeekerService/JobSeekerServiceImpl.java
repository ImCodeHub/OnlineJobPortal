package com.example.OnlineJobPortal.Service.ServiceImpl.JobSeekerService;

import com.example.OnlineJobPortal.Entity.JobApplication;
import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Exception.CustomerException.*;
import com.example.OnlineJobPortal.Model.VisibleJobPost;
import com.example.OnlineJobPortal.Repository.JobApplicationRepository;
import com.example.OnlineJobPortal.Repository.JobPostRepository;
import com.example.OnlineJobPortal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerInterface {
    @Autowired
    private final JobPostRepository jobPostRepository;

    @Autowired
    private final UserRepository userRepository;
    
    @Autowired
    private final JobApplicationRepository jobApplicationRepository;

    @Override
    public List<VisibleJobPost> getAllJobPostFromDb() {
        List<VisibleJobPost> jobList = new ArrayList<>();

        List<JobPost> allJobPosts = jobPostRepository.findAll();

        for (JobPost jobPost : allJobPosts) {
            VisibleJobPost visibleJobPost = VisibleJobPost.builder()
                    .title(jobPost.getTitle())
                    .companyName(jobPost.getCompanyName())
                    .companyLink(jobPost.getCompanyLink())
                    .description(jobPost.getDescription())
                    .city(jobPost.getCity())
                    .state(jobPost.getState())
                    .jobType(jobPost.getJobType())
                    .salary(jobPost.getSalary()).build();

            jobList.add(visibleJobPost);
        }
        return jobList;
    }

    @Override
    public String applyToJob(User user, Long jobPostId) {
        JobPost jobPost = jobPostRepository.findById(jobPostId).orElseThrow(() -> new JobPostNotFoundException("Job Post Not Found by this Id: " + jobPostId));
        User foundUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User Not Found By this Id: " + user.getId()));
        JobApplication jobApplication = JobApplication.builder()
                .jobSeeker(foundUser)
                .jobPost(jobPost)
                .status("Applied")
                .build();
        jobApplicationRepository.save(jobApplication);
        return "you have applied successfully!";
    }
}
