package com.example.practice.dto.UserProfile;

import com.example.practice.dto.User.AppUser;
import com.example.practice.dto.User.AppUserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

public class AppUserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private Boolean isVerified;

    private LocalDateTime modifiedDate;

    private AppUserType userType;
}
