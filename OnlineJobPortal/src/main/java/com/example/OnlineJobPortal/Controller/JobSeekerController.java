package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.Entity.Profile;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Service.ServiceImpl.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobSeeker/v1")
public class JobSeekerController {
    @Autowired
    private final ProfileService profileService;
    @PutMapping("create-profile")
    public ResponseEntity<String> completeProfile(@AuthenticationPrincipal User user, @RequestBody Profile profile){
        String response = profileService.saveProfile(user, profile);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
