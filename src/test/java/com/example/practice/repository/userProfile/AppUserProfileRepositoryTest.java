package com.example.practice.repository.userProfile;

import com.example.practice.model.User.AppUserType;
import com.example.practice.model.UserProfile.AppUserProfile;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
@Slf4j
public class AppUserProfileRepositoryTest {

    @Autowired
    private AppUserProfileRepository profileRepository;

    private AppUserProfile profile;

    @BeforeEach
    public void initSetUp(){
        profile = new AppUserProfile();
    }

    @Test
    @DisplayName("Create a new profile")
    public void test_createProfile(){
        profile.setFirstName("Spencer");
        profile.setLastName("Bull");
        profile.setIsVerified(true);
        profile.setPassword("love");
        profile.setEmail("08082167764");
        profile.setUserType(AppUserType.USER);
        profile.setModifiedDate(LocalDateTime.now());
        assertDoesNotThrow(() ->{
            profileRepository.save(profile);
        });
    }

    @Test
    @DisplayName("Find user profile")
    public void test_findAppUserProfileById() {
        AppUserProfile savedProfile = profileRepository.findById(1).orElse(null);
        log.info("User profile -> {}", savedProfile);
        Assertions.assertThat(savedProfile).isNotNull();
    }

    @Test
    @DisplayName("Read all users profile")
    public void test_readAllAppUserProfiles(){
        List<AppUserProfile> userProfileList = profileRepository.findAll();
        Assertions.assertThat(userProfileList).isNotNull();
        log.info("List of all user profiles --> {}", userProfileList);
    }

    @Test
    @DisplayName("Update user profile")
    public void test_updateProfile(){
        profile = profileRepository.findById(1).orElse(null);
        Assertions.assertThat(profile).isNotNull();
        profile.setPassword("OnHover");
        profileRepository.save(profile);
        org.junit.jupiter.api.Assertions.assertEquals("OnHover", profile.getPassword());
    }
}