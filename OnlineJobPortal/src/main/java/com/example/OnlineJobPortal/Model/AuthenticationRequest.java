package com.example.OnlineJobPortal.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {
    private String userName; //email
    private String password;
}
