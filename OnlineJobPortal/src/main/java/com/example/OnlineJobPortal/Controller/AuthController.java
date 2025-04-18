package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.Model.AuthenticationRequest;
import com.example.OnlineJobPortal.Model.AuthenticationResponse;
import com.example.OnlineJobPortal.Model.RegisterRequest;
import com.example.OnlineJobPortal.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/api/v1")
public class AuthController {
    @Autowired
    private final UserService userService;

    @PostMapping("user-registration")
    public ResponseEntity<AuthenticationResponse> userRegistration(@RequestBody RegisterRequest registerRequest) {
        AuthenticationResponse accessToken = userService.saveUser(registerRequest);
        return new ResponseEntity<>(accessToken, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> userLogin(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse accessToken = userService.authentication(authenticationRequest);
        return new ResponseEntity<>(accessToken, HttpStatus.FOUND);
    }

}
