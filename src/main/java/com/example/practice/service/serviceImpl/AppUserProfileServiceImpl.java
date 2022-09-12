package com.example.practice.service.serviceImpl;

import com.example.practice.dto.request.ChangePasswordDto;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import com.example.practice.dto.request.UpdateAppUserProfileDto;
import com.example.practice.exception.AppException;
import com.example.practice.model.UserProfile.AppUserProfile;
import com.example.practice.repository.userProfile.AppUserProfileRepository;
import com.example.practice.service.AppUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppUserProfileServiceImpl implements AppUserProfileService {

    @Autowired
    AppUserProfileRepository appUserRepository;

    @Override
    public void createUserProfile(AppUserProfile appUserProfile) throws AppException {
        appUserRepository.save(appUserProfile);
    }

    @Override
    public AppUserProfile findUserProfileById(int id) throws AppException {
        if (appUserRepository.existsById(id)) {
            return appUserRepository.findById(id).get();
        }
        else {
            throw new AppException("User with id " + id + " does not exist.");
        }
    }

    @Override
    public List<AppUserProfile> findAllUserProfiles() {
        return appUserRepository.findAll();
    }

    @Override
    public void deleteUserProfileById(int id) throws AppException {
        if (appUserRepository.existsById(id)) {
            appUserRepository.deleteById(id);
        }else {
            throw new AppException("User with id " + id + " does not exist.");
        }
    }

    @Override
    public void updateUserProfile(UpdateAppUserProfileDto updateProfileDto) throws AppException {
        AppUserProfile existingUser = appUserRepository.findById(updateProfileDto.getId()).get();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(updateProfileDto, existingUser);
        existingUser.setModifiedDate(LocalDateTime.now());
        appUserRepository.save(existingUser);
    }


    public void changePassword(ChangePasswordDto changePasswordDto) throws AppException {
        AppUserProfile profile = findUserProfileById(changePasswordDto.getAppUserId());
        if (profile == null){
            throw new AppException("User profile does not exist.");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), profile.getPassword())){
            throw new AppException("The old password is incorrect.");
        }
        profile.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()));
        appUserRepository.save(profile);
    }

    public boolean userProfileDoesNotExist(String email) {
        return !appUserRepository.existsByEmail(email);
    }
}