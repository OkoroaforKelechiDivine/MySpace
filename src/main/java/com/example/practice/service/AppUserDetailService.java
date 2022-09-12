package com.example.practice.service;

import com.example.practice.model.User.AppUserType;
import com.example.practice.model.UserProfile.AppUserProfile;
import com.example.practice.repository.userProfile.AppUserProfileRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    AppUserProfileRepository visitorProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String anyString) throws UsernameNotFoundException {
        AppUserProfile user = visitorProfileRepository.findByEmail(anyString);
        if (user == null) {
            throw new UsernameNotFoundException("Visitor does not exist.");
        }
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user.getUserType()));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(AppUserType role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(role)));
        return grantedAuthorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(AppUserType role){
        return getGrantedAuthorities(role);
    }
}
