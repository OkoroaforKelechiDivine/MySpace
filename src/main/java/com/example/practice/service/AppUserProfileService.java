package com.example.practice.service;

import com.example.practice.dto.request.UpdateAppUserProfileDto;
import com.example.practice.exception.AppException;
import com.example.practice.model.UserProfile.AppUserProfile;
import java.util.List;

public interface AppUserProfileService {

    AppUserProfile findUserProfileById(int id) throws AppException;

    List<AppUserProfile> findAllUserProfiles();

    void deleteUserProfileById(int id) throws AppException;

    void updateUserProfile(UpdateAppUserProfileDto updateProfileDto) throws AppException;

    void createUserProfile(AppUserProfile visitorProfile) throws AppException;
}
