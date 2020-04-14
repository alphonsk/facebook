package com.george.facebook.service;

import com.george.facebook.model.User;
import com.george.facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> compUserOpt = userRepository.findById(username);
        if (compUserOpt  == null ) {
            throw new UsernameNotFoundException("Username not found");
        }
        User user = compUserOpt.get();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthority());
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }

    public User save(User user) {
        if (user.getAuthority() == null)
           user.setAuthority("ROLE_USER");
        if (user.getEnabled() == 0)
            user.setEnabled(1);

        //
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
}
