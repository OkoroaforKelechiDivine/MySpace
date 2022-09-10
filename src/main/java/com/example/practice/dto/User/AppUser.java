package com.example.practice.dto.User;

import com.example.practice.dto.UserProfile.AppUserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private AppUserProfile user;

    private String phoneNumber;

    private String verificationToken;

    @NonNull
    private LocalDateTime createdDate;

    private Gender gender;

    private String dateOfBirth;

    @NonNull
    private Boolean isActive;

    @NonNull
    private LocalDateTime modifiedDate;

}
