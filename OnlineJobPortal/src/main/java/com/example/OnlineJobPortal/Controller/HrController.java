package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.Profile;
import com.example.OnlineJobPortal.Entity.Qualification;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Service.ServiceImpl.HRService.HrServiceImpl;
import com.example.OnlineJobPortal.Service.ServiceImpl.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> jobPost(@AuthenticationPrincipal User user, @RequestBody JobPost jobPost){
        String response = hrService.savePost(user, jobPost);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
