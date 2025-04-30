package com.example.OnlineJobPortal.Model;

import lombok.Data;

@Data
public class JobPostByHrDto {
    private String title;
    private String companyName;
    private String companyLink;
    private String description;
    private String requiredSkills;
    private String city;
    private String state;
    private String jobType;
    private String salary;
}
