package com.example.OnlineJobPortal.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "job_seeker_id")
    private User jobSeeker;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = " Job_post_id")
    private JobPost jobPost;

    private LocalDateTime appliedAt;

    @Column(nullable = false)
    private String status;

    @PrePersist
    @Column(updatable = false)
    protected void onCreate(){
        this.appliedAt = LocalDateTime.now();
    }

}
