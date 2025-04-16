package com.example.OnlineJobPortal.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="qualifications")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String higherEducation;
    private String passoutYear;
    private String institute;
    private String stream;
    private double cgpa;

//    many qualifications can belong to the one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
