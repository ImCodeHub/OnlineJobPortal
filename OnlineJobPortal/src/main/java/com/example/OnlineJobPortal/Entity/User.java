package com.example.OnlineJobPortal.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "date_of_joining", nullable = false)
    private LocalDateTime dateOfJoining;

    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    protected void create(){
        this.dateOfJoining = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Qualification> qualifications = new ArrayList<>();
}
