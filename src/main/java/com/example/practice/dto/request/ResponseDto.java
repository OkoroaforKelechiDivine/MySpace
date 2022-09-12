package com.example.practice.dto.request;

import com.example.practice.model.User.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDto {

    private Integer id;

    private String token;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Boolean isVerified;

    private String createdDate;

    private Gender sex;

    private String verificationToken;

    private Boolean isActive;

    private String modifiedDate;

}
