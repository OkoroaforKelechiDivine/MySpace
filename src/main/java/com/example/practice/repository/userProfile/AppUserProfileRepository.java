package com.example.practice.repository.userProfile;

import com.example.practice.dto.UserProfile.AppUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserProfileRepository extends JpaRepository<AppUserProfile, Integer> {

    AppUserProfile findByEmail(String email);

    Boolean existsByEmail(String email);
}