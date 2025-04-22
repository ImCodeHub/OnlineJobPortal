package com.example.OnlineJobPortal.Service.ServiceImpl.HRService;

import com.example.OnlineJobPortal.Entity.JobPost;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Exception.CustomerException.*;
import com.example.OnlineJobPortal.Repository.JobPostRepository;
import com.example.OnlineJobPortal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HrServiceImpl implements HrServiceInterface{
    @Autowired
    private final JobPostRepository jobPostRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public String savePost(User user, JobPost jobPost) {
        User foundUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User not found by the id"));
        jobPost.setUser(foundUser);
        jobPostRepository.save(jobPost);
        return "Your Post has been submitted.";
    }
}
