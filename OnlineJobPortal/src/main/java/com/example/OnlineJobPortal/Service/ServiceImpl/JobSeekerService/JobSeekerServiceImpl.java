package com.example.OnlineJobPortal.Service.ServiceImpl.JobSeekerService;

import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Model.VisibleJobPost;
import com.example.OnlineJobPortal.Repository.JobPostRepository;
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
}
