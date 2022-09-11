package com.example.practice.dto.admin;

//import com.example.practice.dto.BaseUser;
import com.example.practice.dto.User.AppUserType;
import com.example.practice.dto.UserProfile.AppUserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime createdDate;

    private Boolean isActive;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private AppUserType userType;

    @OneToOne
    private AppUserProfile user;

}