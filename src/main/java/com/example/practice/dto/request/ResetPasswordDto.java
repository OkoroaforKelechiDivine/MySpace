package com.example.practice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResetPasswordDto {

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;
}