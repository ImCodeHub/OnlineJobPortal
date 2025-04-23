package com.example.OnlineJobPortal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String profileSummery;
    @Column(length = 10, nullable = true)
    private String mobile;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private String state;
    @Column(nullable = true)
    private String country;
    @Column(nullable = true)
    private int workExperience;
    private String currentCompony;

    //  one profile can have one user.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String imageName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime profileDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;


    @PrePersist
    protected void onCreate(){
        this.profileDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.updateDate = LocalDateTime.now();
    }

}
