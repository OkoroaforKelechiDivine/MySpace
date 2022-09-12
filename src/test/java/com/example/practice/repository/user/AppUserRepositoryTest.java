package com.example.practice.repository.user;

import com.example.practice.model.User.AppUser;
import com.example.practice.model.User.Gender;
import com.example.practice.model.UserProfile.AppUserProfile;
import com.example.practice.repository.userProfile.AppUserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j

public class AppUserRepositoryTest {


    @Autowired
    private AppUserRepository userRepository;


    @Autowired
    private AppUserProfileRepository profileRepository;

    private AppUser appUser;

    @BeforeEach
    public void startUpMethod(){
        appUser = new AppUser();
    }

    @Test
    @DisplayName("Create a new user")
    public void test_registerUser(){
        AppUserProfile profile = profileRepository.findById(1).get();
        appUser.setPhoneNumber("okoroaforkelechi123@gmail.com");
        appUser.setCreatedDate(LocalDate.now());
        appUser.setModifiedDate(LocalDateTime.now());
        appUser.setDateOfBirth("16/12/2002");
        appUser.setGender(Gender.MALE);
        appUser.setIsActive(false);
        appUser.setUser(profile);
        userRepository.save(appUser);
        Assertions.assertThat(appUser.getId()).isNotNull();
        log.info("User after creation -> {}", appUser);
    }

    @Test
    @DisplayName("Find user")
    public void test_findUserById() {
        appUser = userRepository.findById(1).orElse(null);
        log.info("User details --> {}", appUser);
    }

    @Test
    @DisplayName("Find all users")
    public void test_findAllVisitors() {
        List<AppUser> appUsers = userRepository.findAll();
        log.info("List of users --> {}", appUsers);
    }

    @Test
    @DisplayName("Delete user account")
    public void test_deleteAppUserAccount () {
        appUser = userRepository.findById(1).orElse(null);
        Assertions.assertThat(userRepository.existsById(1)).isTrue();
        userRepository.deleteById(1);
    }

    @Test
    @DisplayName("Update user account")
    public void test_updateUserAccount(){
        appUser = userRepository.findById(1).orElse(null);
        Assertions.assertThat(appUser).isNotNull();
        appUser.setPhoneNumber("09152624528");
        userRepository.updateAppUser(appUser);
        org.junit.jupiter.api.Assertions.assertEquals("09152624528", appUser.getPhoneNumber());

    }
}