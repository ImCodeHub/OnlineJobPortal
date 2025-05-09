package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.Entity.Profile;
import com.example.OnlineJobPortal.Entity.Qualification;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Model.VisibleJobPost;
import com.example.OnlineJobPortal.Service.ServiceImpl.JobSeekerService.JobSeekerServiceImpl;
import com.example.OnlineJobPortal.Service.ServiceImpl.ProfileService;
import com.sun.net.httpserver.HttpsServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobSeeker/v1")
@PreAuthorize("hasRole('JOBSEEKER')")
@Tag(name = "Job Seeker", description = "this is a job seeker APIs to perform all the operation related to Job seeker user")
public class JobSeekerController {
    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final JobSeekerServiceImpl jobSeekerService;

    @Operation(summary = "create profile", description = "this end point send profile data to create a job seeker profile")
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

    @GetMapping("all-jobs")
    public ResponseEntity<List<VisibleJobPost>> getAllJob(
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String jobType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue= "10" ) int size

    ){
        List<VisibleJobPost> jobList = jobSeekerService.getAllJobsWithFilter(skills, city, state, jobType, page,  size);
        return new ResponseEntity<>(jobList, HttpStatus.FOUND);
    }

    @PostMapping("apply-to-job/{id}")
    public ResponseEntity<?> applyJob(@AuthenticationPrincipal User user, @PathVariable Long id){
        String response = jobSeekerService.applyToJob(user, id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
