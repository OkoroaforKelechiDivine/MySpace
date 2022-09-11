package com.example.practice.repository.admin;

import com.example.practice.dto.UserProfile.AppUserProfile;
import com.example.practice.dto.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    default void updateAdmin(Admin varAdmin) {
        varAdmin.setCreatedDate(LocalDateTime.now());
        save(varAdmin);
    }

    boolean existsByEmail(String email);

    Admin findByUser(AppUserProfile user);
}