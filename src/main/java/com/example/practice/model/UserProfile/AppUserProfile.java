package com.example.practice.model.UserProfile;

import com.example.practice.model.User.AppUserType;
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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private Boolean isVerified;

    private LocalDateTime modifiedDate;

    private AppUserType userType;
}
