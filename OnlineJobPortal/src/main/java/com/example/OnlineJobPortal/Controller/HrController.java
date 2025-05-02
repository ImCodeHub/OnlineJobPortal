package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.Profile;
import com.example.OnlineJobPortal.Entity.Qualification;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Model.JobPostByHrDto;
import com.example.OnlineJobPortal.Model.JobSeekerDto;
import com.example.OnlineJobPortal.Model.JobSeekerProfileDto;
import com.example.OnlineJobPortal.Service.ServiceImpl.HRService.HrServiceImpl;
import com.example.OnlineJobPortal.Service.ServiceImpl.ProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hr/v1")
@PreAuthorize("hasRole('HR')")
@RequiredArgsConstructor
public class HrController {
    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final HrServiceImpl hrService;

    @PutMapping("create-profile")
    public ResponseEntity<String> completeProfile(@AuthenticationPrincipal User user, @RequestBody Profile profile){
        String response = profileService.saveProfile(user, profile);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("add-qualification")
    public ResponseEntity<String> addQualification(@AuthenticationPrincipal User user, @RequestBody Qualification qualification){
        String response = profileService.saveQualification(user, qualification);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("job-post")
    public ResponseEntity<String> jobPost(@AuthenticationPrincipal User user, @RequestBody JobPostByHrDto jobPost){
        String response = hrService.savePost(user, jobPost);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("get-all-jobPost")
    public ResponseEntity<?> getAllJobPost(@AuthenticationPrincipal User user){
        List<JobPost> yourPost = hrService.getYourPost(user.getId());
        return new ResponseEntity<>(yourPost, HttpStatus.OK);
    }

    // to view applicant on a job post
    @GetMapping("get-applicants/{jobPostId}")
    public ResponseEntity<?>getApplicantList(@PathVariable Long jobPostId){
        List<JobSeekerDto> applicants = hrService.getApplicant(jobPostId);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    // to view job seeker profile
    @GetMapping("get-applicant-profile/{userId}")
    public ResponseEntity<?>getApplicantProfile(@PathVariable Long userId, @RequestParam Long jobApplicationId){
        JobSeekerProfileDto jobSeekerProfile = hrService.getJobSeekerProfile(userId, jobApplicationId);
        return new ResponseEntity<>(jobSeekerProfile, HttpStatus.OK);
    }

    @PutMapping("reject-applicant/{applicationId}")
    public ResponseEntity<String> rejectJobSeeker(@PathVariable Long applicationId)  {
        String response = hrService.rejectJobSeeker(applicationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("shortlist-applicant/{applicationId}")
    public ResponseEntity<String> shortlistJobSeeker(@PathVariable Long applicationId)  {
        String response = hrService.shortlistJobSeeker(applicationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("inactive-jobPost/{id}")
    public ResponseEntity<String> inactiveThePost(@PathVariable(name = "id") Long jobPostId){
        String response = hrService.inactiveJobPost(jobPostId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
