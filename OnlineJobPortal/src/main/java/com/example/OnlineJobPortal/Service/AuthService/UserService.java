package com.example.OnlineJobPortal.Service.AuthService;

import com.example.OnlineJobPortal.Entity.Role;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Exception.CustomerException.*;
import com.example.OnlineJobPortal.Model.AuthenticationRequest;
import com.example.OnlineJobPortal.Model.AuthenticationResponse;
import com.example.OnlineJobPortal.Model.RegisterRequest;
import com.example.OnlineJobPortal.Repository.UserRepository;
import com.example.OnlineJobPortal.Service.JwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    //    user registration
    public AuthenticationResponse saveUser(RegisterRequest registerRequest) {
        userRepository.findByEmail(registerRequest.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExistException("user Already exist in DB");
        });
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.valueOf("JOBSEEKER"))
                .build();

        User savedUser = userRepository.save(user);
//        generate token with secret (jwt)
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwt).build();
    }

    //    user login Authentication
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        User user = userRepository.findByEmail(request.getUserName()).orElseThrow(() -> new UserNotFoundException("User Not Found by this E-mail: "+ request.getUserName()));
        //generate token with secret (jwt)
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwt).build();
    }

    //    user registration
    public AuthenticationResponse saveHr(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.valueOf("HR"))
                .build();

        User savedUser = userRepository.save(user);
//        generate token with secret (jwt)
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwt).build();
    }
}
