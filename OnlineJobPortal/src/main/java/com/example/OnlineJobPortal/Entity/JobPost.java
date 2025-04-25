package com.example.OnlineJobPortal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job_posts")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String companyLink;

    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String jobType;
    @Column(nullable = false)
    private String salary;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobPostStatus status;

    //Many JobPost can belong to One HR
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hr_id")
    @JsonIgnore
    private User hr;

    //One Post can have Many HR
    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<JobApplication> jobApplications = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime postDate;

    @PrePersist
    protected void onCreate() {
        this.postDate = LocalDateTime.now();
    }


}
