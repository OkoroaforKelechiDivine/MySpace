package com.example.practice.dto.request;

import com.example.practice.model.User.AppUserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppUserProfileDto {

    private int id;

    private String firstName;

    private String lastName;

    private AppUserType userType;
}