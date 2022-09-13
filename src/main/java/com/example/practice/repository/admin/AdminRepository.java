package com.example.practice.repository.admin;

import com.example.practice.model.UserProfile.AppUserProfile;
import com.example.practice.model.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    default void updateAdmin(Admin admin) {
        admin.setCreatedDate(LocalDateTime.now());
        save(admin);
    }

    boolean existsByEmail(String email);

    Admin findByUser(AppUserProfile user);
}