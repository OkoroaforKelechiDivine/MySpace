package com.example.practice.repository.user;

import com.example.practice.model.User.AppUser;
import com.example.practice.model.UserProfile.AppUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByPhoneNumber(String phoneNumber);

    AppUser findByUser(AppUserProfile user);

    AppUser findAppUserById(int id);

    Boolean existsByPhoneNumber(String phoneNumber);

    default  void updateAppUser(AppUser appUser){
        appUser.setModifiedDate(LocalDateTime.now());
        save(appUser);
    }

    List<AppUser> findAll();

    AppUser findByVerificationToken(String verificationToken);
}
