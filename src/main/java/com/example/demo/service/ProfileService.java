package com.example.demo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.Repository.ProfileRepository;

import com.example.demo.model.Profile;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void saveProfile(Profile profile) {
        if (profile.getId() == null) {
            profileRepository.save(profile); 
        } else {
            profileRepository.saveAndFlush(profile); 
        }
    }

    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile findProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }


    
}
