package com.dbs.bankingservice.security;

import com.dbs.bankingservice.entities.User;
import com.dbs.bankingservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);
        User userFromDatabase = userRepository.findByEmail(email);
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + email + " was not found");
        }
        List<SimpleGrantedAuthority> authorities = userFromDatabase.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
        return new org.springframework.security.core.userdetails.User(userFromDatabase.getEmail(), userFromDatabase.getPassword(), authorities);
    }

}
