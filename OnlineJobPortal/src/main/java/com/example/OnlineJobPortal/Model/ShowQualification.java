package com.example.OnlineJobPortal.Model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowQualification {
    private String higherEducation;
    private String passOutYear;
    private String institute;
    private String stream;
    private double cgpa;
}
