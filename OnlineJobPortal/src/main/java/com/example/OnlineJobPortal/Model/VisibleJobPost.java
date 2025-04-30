package com.example.OnlineJobPortal.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisibleJobPost {
    private Long id;
    private String title;
    private String companyName;
    private String skills;
    private String companyLink;
    private String description;
    private String city;
    private String state;
    private String jobType;
    private String salary;
}
