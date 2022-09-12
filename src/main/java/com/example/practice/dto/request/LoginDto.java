package com.example.practice.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class LoginDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String type;
}
