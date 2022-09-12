package com.example.practice.controller.appUser;

import com.example.practice.dto.request.RegisterDto;
import com.example.practice.dto.request.ResetPasswordDto;
import com.example.practice.dto.request.ResponseDto;
import com.example.practice.dto.request.UpdateAppUserDto;
import com.example.practice.dto.response.ResponseDetails;
import com.example.practice.dto.response.ResponseDetailsWithObject;
import com.example.practice.exception.AppException;
import com.example.practice.model.User.AppUser;
import com.example.practice.model.UserProfile.AppUserProfile;
import com.example.practice.service.serviceImpl.AppUserProfileServiceImpl;
import com.example.practice.service.serviceImpl.AppUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AppUserController {

    @Autowired
    private AppUserServiceImpl userService;

    AppUser appUser;

    @Autowired
    AppUserProfileServiceImpl appUserProfileService;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("")
    public ResponseEntity<?> registerAppUser(@Valid @RequestBody RegisterDto registerDto, HttpServletRequest httpServletRequest) throws Exception {
        if (appUserProfileService.userProfileDoesNotExist(registerDto.getEmail())) {
            AppUserProfile profile = modelMapper.map(registerDto, AppUserProfile.class);
            userService.registerAppUser(profile);
        }
        else {
            throw new AppException("User with that email already exist.");
        }
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Your account has been created successfully.", HttpStatus.OK.toString());
        return ResponseEntity.status(200).body(responseDetails);
    }

    @GetMapping("/verify/{email}")
    public ResponseEntity<?> verifyIfAppUserExist(@PathVariable String email) throws AppException {
        ResponseDto responseDto;
        if (email == null){
            throw new AppException("User email shouldn't be empty.");
        }else {
            responseDto = userService.checkIfAppUserExists(email);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto) throws AppException {
        userService.resetPassword(resetPasswordDto.getEmail(), resetPasswordDto.getPassword());
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Password have been changed successfully.", "success");
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyAppUserEmail(@RequestParam("email") String email) throws AppException {
        AppUser appUser = userService.verifyAppUser(email);
        ResponseDetailsWithObject responseDetails = new ResponseDetailsWithObject(LocalDateTime.now(), "Visitor confirmation successful.",appUser, "success");
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAppUserById(@PathVariable int id) throws AppException {
        if (userService.appUserDoesNotExist(id)){
            throw new AppException("User with that id does not exist.");
        }
        userService.findAppUserById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> findAllAppUsers() {
        List<AppUser> userList = userService.findAllAppUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateAppUser(@Valid @RequestBody UpdateAppUserDto updateAppUserDto) throws AppException {
        if (updateAppUserDto.getId() == null) {
            throw new AppException("User id is empty.");
        }
        if (userService.appUserDoesNotExist(updateAppUserDto.getId())){
            throw new AppException("User with that id does not exist.");
        }
        AppUser user = userService.updateAppUser(updateAppUserDto);
        ResponseDetailsWithObject responseDetails = new ResponseDetailsWithObject(LocalDateTime.now(), "User's account has been updated successfully.", user, "success");
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppUser(@PathVariable int id) throws AppException {
        if (userService.appUserDoesNotExist(id)){
            throw new AppException("User with that id does not exist.");
        }
        userService.deleteAppUserById(id);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "User's account has been deactivated successfully.", "success");
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }
}
