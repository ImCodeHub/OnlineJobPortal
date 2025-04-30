package com.example.OnlineJobPortal.Model;

import com.example.OnlineJobPortal.Entity.Qualification;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobSeekerProfileDto {
    private Long jobApplicationId;
    private String fullName;
    private String email;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String country;
    private int workExperience;
    private String currentCompany;
    private String profileSummery;
    private List<Qualification> qualifications;
    private String imageName; // For profile pic
    private String resumeLink; // (optional - add after you implement resume upload)
}
