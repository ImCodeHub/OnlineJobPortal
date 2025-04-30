package com.example.OnlineJobPortal.Model;

import com.example.OnlineJobPortal.Entity.Qualification;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobSeekerDto {
    private Long jobSeekerId;
    private Long jobApplicationId;
    private String fullName;
    private String companyName;
    private int experience;
}
