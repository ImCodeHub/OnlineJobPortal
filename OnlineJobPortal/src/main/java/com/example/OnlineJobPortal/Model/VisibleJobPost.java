package com.example.OnlineJobPortal.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VisibleJobPost {
    private String title;
    private String companyName;
    private String companyLink;
    private String description;
    private String city;
    private String state;
    private String jobType;
    private String salary;
}
