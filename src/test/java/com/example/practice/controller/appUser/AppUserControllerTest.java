package com.example.practice.controller.appUser;

import com.example.practice.dto.request.LoginDto;
import com.example.practice.dto.request.RegisterDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static javax.swing.UIManager.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest

public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    LoginDto loginDto;

    private ObjectMapper objectMapper;

    private RegisterDto registerDto;

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlemVraWVsYWtpbnR1bmRlMUBnbWFpbC5jb20iLCJleHAiOjE2NjM4MzM3Nzl9.bgUeLqfKgMeaO_--tM9quK5wytj6tZSu52D4mTCVrdP7qP3U62exbSLY5hGa3C1MCjJoKKo1TolEGbCd_Qx6DQ";

    @BeforeEach
    public void startUpMethod(){
        objectMapper = new ObjectMapper();
        registerDto = new RegisterDto();
        loginDto = new LoginDto();
    }

    @Test
    @DisplayName("Create account")
    public void test_createUserAccount() throws Exception {
        registerDto.setPassword("I love Jesus");
        registerDto.setFirstName("benson");
        registerDto.setLastName("idahosa");
        registerDto.setEmail("ezekielakintunde1@gmail.com");

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Create bad account")
    public void test_createUserAccountWithBadCredentials() throws Exception {
        registerDto.setPassword("email");
        registerDto.setFirstName("");
        registerDto.setLastName("");
        registerDto.setEmail("ezekielakintunde12@gmail.com");

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void test_userLogin() throws Exception {
        loginDto.setPassword("I love Jesus");
        loginDto.setEmail("ezekielakintunde1@gmail.com");
        this.mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

//    @Test
//    @DisplayName("Verify user account")
//    public void test_verifyUserAccount(){
//        this.mockMvc.perform(get("/verify/kelechi@gmail.com")
//                        .header("Authorization", token))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    public void test_visitorCanWithBadLoginCredentials() throws Exception {
//        loginDto.setPassword("email");
//        loginDto.setEmail("08087643362");
//
//        this.mockMvc.perform(post("/user/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginDto)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//    }
//
//    @Test
//    public void test_findVisitorById() throws Exception {
//        this.mockMvc.perform(get("/users/621519a468f0a2213ffec4a4")
//                        .header("Authorization", token))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//    }

//
//    @Test
//    public void test_findVisitorByIdThatDoesExist() throws Exception {
//
//        this.mockMvc.perform(get("/users/621519a468f0a2213ffec4a4")
//                        .header("Authorization", token))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    public void test_findAllVisitors() throws Exception {
//        this.mockMvc.perform(get("/all-visitors")
//                        .header("Authorization", token))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//    }
}