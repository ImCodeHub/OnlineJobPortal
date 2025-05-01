package com.example.OnlineJobPortal.Model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowProfile {
    private String profileSummery;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String country;
    private int workExperience;
    private String currentCompony;
}
