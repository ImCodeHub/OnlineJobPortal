package com.example.OnlineJobPortal.Model;

import com.example.OnlineJobPortal.Entity.Qualification;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobSeekerDto {
    private String fullName;
    private String companyName;
    private List<Qualification> qualifications;
    private int experience;


}
