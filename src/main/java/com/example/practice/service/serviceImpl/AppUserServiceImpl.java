package com.example.practice.service.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.practice.dto.request.ResponseDto;
import com.example.practice.dto.request.UpdateAppUserDto;
import com.example.practice.model.User.AppUser;
import com.example.practice.model.User.AppUserType;
import com.example.practice.model.User.Gender;
import com.example.practice.model.UserProfile.AppUserProfile;
import com.example.practice.exception.AppException;
import com.example.practice.repository.user.AppUserRepository;
import com.example.practice.repository.userProfile.AppUserProfileRepository;
import com.example.practice.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.example.practice.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.practice.security.SecurityConstants.SECRET;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserProfileRepository appUserProfileRepository;

    private AppUserProfile appUserProfile;

    @Autowired
    AppUserProfileServiceImpl appUserProfileService;

    @Autowired
    AppUserRepository appUserRepository;

    public Boolean appUserDoesNotExist(int id) {
        return !appUserRepository.existsById(id);
    }

    @Override
    public void registerAppUser(AppUserProfile appUserProfile) throws AppException {

        if(appUserProfile.getPassword().length() < 6){
            throw new AppException("Password must have at least 6 characters.");
        }
        AppUser appUser = new AppUser();
        appUserProfile.setModifiedDate(LocalDateTime.now());
        appUserProfile.setIsVerified(false);
        appUserProfile.setUserType(AppUserType.USER);
        appUserProfile.setPassword(encryptPassword(appUserProfile.getPassword()));
        appUserProfileService.createUserProfile(appUserProfile);

        appUser.setUser(appUserProfile);
        appUser.setCreatedDate(LocalDateTime.now());
        appUser.setIsActive(true);
        appUser.setDateOfBirth("");
        appUser.setGender(null);
        appUserRepository.save(appUser);
    }

    @Override
    public void verifyEmailToken(String verificationToken, String url) throws AppException {
        AppUser byVerificationToken = appUserRepository.findByVerificationToken(verificationToken);

        if (byVerificationToken == null) {
            throw new AppException("User token should not be empty.");
        }
        else if (byVerificationToken.getUser().getIsVerified()) {
            throw new AppException("User has been verified.");
        }
        byVerificationToken.setVerificationToken(null);
        byVerificationToken.getUser().setIsVerified(true);
        appUserRepository.save(byVerificationToken);
    }

    @Override
    public void resetPassword(String oldPassword, String newPassword) throws AppException {
        AppUserProfile appUserProfileVerificationEmail = appUserProfileRepository.findByEmail(oldPassword);
        AppUser userVerificationEmail = appUserRepository.findByUser(appUserProfileVerificationEmail);

        if (userVerificationEmail == null) {
            throw new AppException("User id can't be null.");
        } else {
            userVerificationEmail.getUser().setPassword(encryptPassword(newPassword));
            appUserRepository.save(userVerificationEmail);
            appUserProfileVerificationEmail.setPassword(userVerificationEmail.getUser().getPassword());
            appUserProfileRepository.save(appUserProfileVerificationEmail);
        }
    }

    @Override
    public AppUser findAppUserByEmail(String email) {
        return appUserRepository.findByPhoneNumber(email);
    }

    @Override
    public AppUser findAppUserById(Integer id) throws AppException {
        if (id == null){
            throw new AppException("User id should not be null.");
        }
        return appUserRepository.findAppUserById(id);
    }

    @Override
    public void deleteAppUserById(int id) throws AppException {
        AppUser userId = appUserRepository.findById(id).orElseThrow(()-> new AppException("The user id doesn't exist."));
        if (!userId.getIsActive()) {
            throw new AppException("The user has already been deactivated.");
        }
        appUserRepository.deleteById(id);
    }

    @Override
    public List<AppUser> findAllAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser updateAppUser(UpdateAppUserDto updateAppUserDto) throws AppException {
        AppUser existingUser = findAppUserById(updateAppUserDto.getId());

        if (!existingUser.getIsActive()) {
                throw new AppException("The user id has been deactivated.");
        }
        if (updateAppUserDto.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(updateAppUserDto.getPhoneNumber());
        }
        if (updateAppUserDto.getGender() != null) {
            existingUser.setGender(Gender.valueOf(String.valueOf(updateAppUserDto.getGender())));
        }
        if (updateAppUserDto.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(updateAppUserDto.getDateOfBirth());
        }
        if (updateAppUserDto.getFirstName() != null) {
            existingUser.getUser().setFirstName(updateAppUserDto.getFirstName());
        }
        if (updateAppUserDto.getLastName() != null) {
            existingUser.getUser().setLastName(updateAppUserDto.getLastName());
        }
        if (updateAppUserDto.getEmail() != null) {
            existingUser.getUser().setEmail(updateAppUserDto.getEmail());
        }
        existingUser.setModifiedDate(LocalDateTime.now());
        appUserProfileRepository.save(existingUser.getUser());
        appUserRepository.save(existingUser);
        return existingUser;
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public ResponseDto checkIfAppUserExists(String email) throws AppException {
        if (!appUserProfileRepository.existsByEmail(email)) {
            throw new AppException("User with that email does not exist.");
        }
        else {
            AppUserProfile userEmail = appUserProfileRepository.findByEmail(email);
            AppUser user = appUserRepository.findByUser(userEmail);
            user.getUser().setIsVerified(Boolean.TRUE);
            appUserRepository.save(user);
            userEmail.setIsVerified(Boolean.TRUE);
            appUserProfileRepository.save(userEmail);
            String token = JWT.create()
                    .withSubject(user.getUser().getFirstName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes(StandardCharsets.UTF_8)));

            ResponseDto responseDto = new ResponseDto();
            responseDto.setEmail(userEmail.getEmail());
            responseDto.setCreatedDate(user.getCreatedDate().toString());
            responseDto.setId(user.getId());
            responseDto.setToken(user.getVerificationToken());
            responseDto.setFirstName(user.getUser().getFirstName());
            responseDto.setLastName(user.getUser().getLastName());
            responseDto.setIsActive(user.getIsActive());
            responseDto.setIsVerified(user.getUser().getIsVerified());
            responseDto.setModifiedDate(user.getModifiedDate().toString());
            responseDto.setPhoneNumber(user.getPhoneNumber());
            responseDto.setSex(user.getGender());
            responseDto.setToken(token);
            return responseDto;
        }
    }

    public AppUser verifyAppUser(String email) throws AppException{
        if (!appUserProfileRepository.existsByEmail(email)) {
            throw new AppException("User email does not exist.");
        }
        AppUserProfile userProfile = appUserProfileRepository.findByEmail(email);
        return appUserRepository.findByUser(userProfile);
    }
}