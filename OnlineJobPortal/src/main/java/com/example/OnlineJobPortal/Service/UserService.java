package com.example.OnlineJobPortal.Service;

import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Model.AuthenticationResponse;
import com.example.OnlineJobPortal.Model.RegisterRequest;
import com.example.OnlineJobPortal.Repository.UserRepository;
import com.example.OnlineJobPortal.Service.JwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    public AuthenticationResponse saveUser(RegisterRequest registerRequest){
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())//we will see it later.
                .role()
                .build();

//        User savedUser = userRepository.save(user);
//        jwtService.generateToken(savedUser);
        return AuthenticationResponse.builder().accessToken().build();


    }
}
