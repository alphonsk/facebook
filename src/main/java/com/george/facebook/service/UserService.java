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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null ) {
            throw new UsernameNotFoundException("email not found");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthority());
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }



    public User save(User user) {
        if (user.getAuthority() == null)
           user.setAuthority("ROLE_USER");
        if (user.getEnabled() == 0)
            user.setEnabled(1);

        // passwords dont match
        if(!(user.getPassword().equals(user.getConfirmPassword()))){
            return null;
        }

        // dont store confrimedPassword
        user.setConfirmPassword("");

        // email already in use
        User myUser = userRepository.findByEmail(user.getEmail());
        if (myUser != null ) {
            return null;
        }



        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // update
    public User update(User user, Long userId) {
        if (user.getAuthority() == null)
            user.setAuthority("ROLE_USER");
        if (user.getEnabled() == 0)
            user.setEnabled(1);

        // passwords dont match
        if(!(user.getPassword().equals(user.getConfirmPassword()))){
            return null;
        }

        // dont store confrimedPassword
        user.setConfirmPassword("");
        //
        User currentUser = findById(userId);
        currentUser.setId(userId);
        currentUser.setEmail(user.getEmail());
        currentUser.setAuthority(user.getAuthority());
        currentUser.setEnabled(user.getEnabled());
        //
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        currentUser.setPassword(encodedPassword);

        return userRepository.save(currentUser);
    }




    public User findByEmail(String email) {
        User user = null;
        return  userRepository.findByEmail(email);

    }

    public User findById(Long userId) {
        User user = null;
        try {
            user = userRepository.findById(userId).get();
        } finally {
            if (user == null)
                return user;
        }
        return user;
    }


    public String printError(String error ){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(" ");
        System.out.println("              " + error);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return "";
    }



//
}
