package com.example.OnlineJobPortal.Service.ServiceImpl.HRService;

import com.example.OnlineJobPortal.Entity.*;
import com.example.OnlineJobPortal.Exception.CustomerException.*;
import com.example.OnlineJobPortal.Model.JobPostByHrDto;
import com.example.OnlineJobPortal.Model.JobSeekerDto;
import com.example.OnlineJobPortal.Model.JobSeekerProfileDto;
import com.example.OnlineJobPortal.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private final QualificationRepository qualificationRepository;

    @Override
    public String savePost(User user, JobPostByHrDto jobPostByHrDto) {
        User foundUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User not found by the id"));
        JobPost jobPost = JobPost.builder()
                .title(jobPostByHrDto.getTitle())
                .companyName(jobPostByHrDto.getCompanyName())
                .companyLink(jobPostByHrDto.getCompanyLink())
                .description(jobPostByHrDto.getDescription())
                .requiredSkills(jobPostByHrDto.getRequiredSkills())
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
                    .jobSeekerId(jobSeekeruser.getId())
                    .jobApplicationId(jobApplication.getId())
                    .fullName(jobSeekeruser.getFirstName() + " " + jobSeekeruser.getLastName())
                    .companyName(profile.getCurrentCompony())
                    .experience(profile.getWorkExperience())
                    .build();
            jobSeekers.add(jobSeekerDto);
        }

        return jobSeekers;
    }

    @Override
    public JobSeekerProfileDto getJobSeekerProfile(Long userId, Long jobApplicationId) {
        User jobSeekerUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found by this id"));
        Profile profile = profileRepository.findByUserId(jobSeekerUser.getId()).orElseThrow(() -> new RuntimeException("profile not found"));
        List<Qualification> qualifications = qualificationRepository.findByUserId(jobSeekerUser.getId());
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId).orElseThrow(() -> new RuntimeException("Job application not found"));
        JobSeekerProfileDto jobSeekerProfileDto = JobSeekerProfileDto.builder()
                .jobApplicationId(jobApplication.getId())
                .fullName(jobSeekerUser.getFirstName()+" "+jobSeekerUser.getLastName())
                .email(jobSeekerUser.getEmail())
                .mobile(profile.getMobile())
                .city(profile.getCity())
                .state(profile.getState())
                .country(profile.getCountry())
                .address(profile.getAddress())
                .workExperience(profile.getWorkExperience())
                .profileSummery(profile.getProfileSummery())
                .currentCompany(profile.getCurrentCompony())
                .imageName(profile.getImageName())
//                .resumeLink(profile.getLink()) //if you want to add later.
                .qualifications(qualifications)
                .build();
        return jobSeekerProfileDto ;
    }

    @Override
    public String rejectJobSeeker(Long jobApplicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId).orElseThrow(() -> new RuntimeException("job application not found"));
        jobApplication.setStatus("Rejected");
        jobApplicationRepository.save(jobApplication);
        return "Job Applicant has Rejected.";
    }

    @Override
    public String shortlistJobSeeker(Long jobApplicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId).orElseThrow(() -> new RuntimeException("Job Application not found"));
        jobApplication.setStatus("Shortlisted");
        jobApplicationRepository.save(jobApplication);
        return "Job Applicant has Shortlisted.";
    }


}
