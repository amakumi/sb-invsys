package com.idmgmt.springboot.service;

import com.idmgmt.springboot.model.User;
import com.idmgmt.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    /*@Override
    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        User user = userRepo.findByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return new CustomUserDetails(user);
    }*/

}
