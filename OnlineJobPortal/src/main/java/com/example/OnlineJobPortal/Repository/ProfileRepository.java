package com.example.OnlineJobPortal.Repository;

import com.example.OnlineJobPortal.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByUserId(Long user);
}
