package com.example.OnlineJobPortal.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDashBoard {
    private Long numberOfUsers;
    private Long numberOfJobSeeker;
    private Long numberOfRecruiter;
    private Long numberOfApplicationThisWeek;
    private Long todayApplications;
}
