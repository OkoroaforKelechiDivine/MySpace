package com.example.practice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import org.intellij.lang.annotations.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {

    @NotEmpty(message = "First name should not be empty.")
//    @NotNull
    @Size(min = 2, max = 20, message = "First name should be 2 to 20 character long")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty.")
    @Size(min = 2, max = 20, message = "Last name should be 2 to 20 character long")
    private String lastName;

    @Pattern(regexp = "^[@]\\s")
    @NotEmpty(message = "email is should not be empty.")
    private String email;

    @NotEmpty(message = "password should not be lesser than 5 characters.")
    private String password;
}