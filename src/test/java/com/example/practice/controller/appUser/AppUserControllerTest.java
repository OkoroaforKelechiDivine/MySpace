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
//import com.fasterxml.jackson.core.JsonProcessingException;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

//    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODA4NzY0MzM2MiIsImV4cCI6MTY0NjQxNjMzN30.WQJFqN_bR1U640y0OX54hajaGL6bAO0XBc70FDNmYgquAr29l5eZFLKMPO0mKbQZsdkbd4Rc55L2fduQNETsJQ";

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
        registerDto.setEmail("ezekielakintunde11@gmail.com");

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

//    @Test
//    @DisplayName("Create bad account")
//    public void test_createVisitorAccountWithBadCredentials() throws Exception {
//        registerDto.setPassword("email");
//        registerDto.setFirstName("");
//        registerDto.setLastName("");
//        registerDto.setEmail("ezekielakintunde12@gmail.com");
//
//        this.mockMvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(registerDto)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//    }
//
//    @Test
//    public void test_visitorCanLogin() throws Exception {
//        loginDto.setPassword("email");
//        loginDto.setEmail("ezekielakintunde18@gmail.com");
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
//
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