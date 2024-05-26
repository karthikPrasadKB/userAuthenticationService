package com.example.UserAuthentication.security.services;

import com.example.UserAuthentication.models.User;
import com.example.UserAuthentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByEmail(username); //Here username == email;
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        User user = optionalUser.get();
        return new CustomUserDetails(user);
    }
}
