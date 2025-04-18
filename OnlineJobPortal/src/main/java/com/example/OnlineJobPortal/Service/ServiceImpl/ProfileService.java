package com.example.OnlineJobPortal.Service.ServiceImpl;

import com.example.OnlineJobPortal.Entity.Profile;
import com.example.OnlineJobPortal.Entity.Qualification;
import com.example.OnlineJobPortal.Entity.User;
import com.example.OnlineJobPortal.Repository.ProfileRepository;
import com.example.OnlineJobPortal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ProfileRepository profileRepository;

    public String saveProfile(User user, Profile profile) {

        User foundUser = userRepository.findById( user.getId()).orElseThrow(() -> new RuntimeException(("user not found")));
        Optional<Profile> optionalProfile = profileRepository.findByUserId(foundUser.getId());
        if (optionalProfile.isPresent()) {
            Profile existingProfile = optionalProfile.get();

            existingProfile.setProfileSummery(profile.getProfileSummery());
            existingProfile.setAddress(profile.getAddress());
            existingProfile.setCity(profile.getCity());
            existingProfile.setCountry(profile.getCountry());
            existingProfile.setState(profile.getState());
            existingProfile.setMobile(profile.getMobile());
            existingProfile.setCurrentCompony(profile.getCurrentCompony());
            existingProfile.setWorkExperience(profile.getWorkExperience());

            profileRepository.save(existingProfile);
            return "your profile has been updated";
        } else {
            profile.setUser(foundUser);
            Profile save = profileRepository.save(profile);
            return "you have created your profile";
        }
    }

}
