package com.example.practice.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import org.intellij.lang.annotations.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {

    @NotNull
    @Size(min = 2, max = 20, message = "First name should be 2 to 20 character long")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20, message = "Last name should be 2 to 20 character long")
    private String lastName;

    @Pattern(regexp = "^[@]\\s")
    @NotEmpty(message = "email is required")
    private String email;

    @NotEmpty(message = "password is should not be lesser than 5 characters")
    private String password;
}