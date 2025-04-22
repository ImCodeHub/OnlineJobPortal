package com.example.OnlineJobPortal.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "qualifications")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String higherEducation;
    @Column(nullable = false)
    private String passOutYear;
    @Column(nullable = false)
    private String institute;
    @Column(nullable = false)
    private String stream;
    @Column(nullable = false)
    private double cgpa;

    //Many qualifications can belong to the One user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
