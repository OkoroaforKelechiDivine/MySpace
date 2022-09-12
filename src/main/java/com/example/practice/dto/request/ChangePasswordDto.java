package com.example.practice.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ChangePasswordDto {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;

    @NotNull
    private String appUserId;
}