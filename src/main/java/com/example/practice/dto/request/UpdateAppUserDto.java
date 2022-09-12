package com.example.practice.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppUserDto{

    @NotNull
    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String gender;

    private String email;

    private String dateOfBirth;
}