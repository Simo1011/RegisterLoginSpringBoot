package com.simo.LoginExample.util;

import com.simo.LoginExample.model.User;
import com.simo.LoginExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database using the UserRepository
        User user = userRepository.findByUsername(username);

        // Check if the user exists
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Return a UserDetails object (use a custom UserDetails implementation if needed)
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}