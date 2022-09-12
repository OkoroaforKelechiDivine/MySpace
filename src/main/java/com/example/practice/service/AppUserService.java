package com.example.practice.service;

import com.example.practice.dto.request.UpdateAppUserDto;
import com.example.practice.model.User.AppUser;
import com.example.practice.model.UserProfile.AppUserProfile;
import com.example.practice.exception.AppException;

import java.util.List;

public interface AppUserService {

    void registerAppUser(AppUserProfile appUserProfile) throws AppException;

    void verifyEmailToken(String verificationToken, String url) throws AppException;

    void resetPassword(String oldPassword, String newPassword) throws AppException;

    AppUser findAppUserByEmail(String email);

    AppUser findAppUserById(Integer id) throws AppException;

    void deleteAppUserById(int id) throws AppException;

    List<AppUser> findAllAppUsers();

    AppUser updateAppUser(UpdateAppUserDto appUserDto) throws AppException;
}