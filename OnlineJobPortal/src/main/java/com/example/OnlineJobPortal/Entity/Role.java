package com.example.OnlineJobPortal.Entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum Role {
    ADMIN,
    HR,
    JOBSEEKER;

    public List<SimpleGrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.name()));
    }
}
