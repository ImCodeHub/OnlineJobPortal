package com.example.OnlineJobPortal.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="qualifications")
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

//    many qualifications can belong to the one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
