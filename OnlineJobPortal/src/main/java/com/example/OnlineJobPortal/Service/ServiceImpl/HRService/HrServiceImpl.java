package com.example.OnlineJobPortal.Service.ServiceImpl.HRService;

import com.example.OnlineJobPortal.Entity.*;
import com.example.OnlineJobPortal.Exception.CustomerException.*;
import com.example.OnlineJobPortal.Model.JobPostByHrDto;
import com.example.OnlineJobPortal.Model.JobSeekerDto;
import com.example.OnlineJobPortal.Repository.JobApplicationRepository;
import com.example.OnlineJobPortal.Repository.JobPostRepository;
import com.example.OnlineJobPortal.Repository.ProfileRepository;
import com.example.OnlineJobPortal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HrServiceImpl implements HrServiceInterface {
    @Autowired
    private final JobPostRepository jobPostRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JobApplicationRepository jobApplicationRepository;

    @Autowired
    private final ProfileRepository profileRepository;

    @Override
    public String savePost(User user, JobPostByHrDto jobPostByHrDto) {
        User foundUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User not found by the id"));
        JobPost jobPost = JobPost.builder()
                .title(jobPostByHrDto.getTitle())
                .companyName(jobPostByHrDto.getCompanyName())
                .companyLink(jobPostByHrDto.getCompanyLink())
                .description(jobPostByHrDto.getDescription())
                .city(jobPostByHrDto.getCity())
                .state(jobPostByHrDto.getState())
                .jobType(jobPostByHrDto.getJobType())
                .salary(jobPostByHrDto.getSalary())
                .status(JobPostStatus.ACTIVE)
                .hr(foundUser).build();

        jobPostRepository.save(jobPost);
        return "Your Post has been submitted.";
    }

    @Override
    public List<JobPost> getYourPost(Long hrId) {
        User hr = userRepository.findById(hrId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        List<JobPost> jobPosts = hr.getJobPosts();
        return jobPosts;
    }

    @Override
    public List<JobSeekerDto> getApplicant(Long jobPostId) {
        List<JobApplication> applications = jobApplicationRepository.findByJobPostId(jobPostId);

        List<JobSeekerDto> jobSeekers = new ArrayList<>();

        for (JobApplication jobApplication : applications) {
            User jobSeekeruser = jobApplication.getJobSeeker();
            User user = userRepository.findById(jobSeekeruser.getId()).orElseThrow(() -> new UserNotFoundException("user not found"));
            Profile profile = profileRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("profile not found"));
            JobSeekerDto jobSeekerDto = JobSeekerDto.builder()
                    .fullName(jobSeekeruser.getFirstName() + " " + jobSeekeruser.getLastName())
                    .qualifications(jobSeekeruser.getQualifications())
                    .companyName(profile.getCurrentCompony())
                    .experience(profile.getWorkExperience())
                    .build();
            jobSeekers.add(jobSeekerDto);
        }

        return jobSeekers;
    }

}
