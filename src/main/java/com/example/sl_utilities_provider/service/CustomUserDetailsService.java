package com.example.sl_utilities_provider.service;
import com.example.sl_utilities_provider.entities.User;
import com.example.sl_utilities_provider.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User usr = userRepo.findUserByEmail(username);
        if (usr == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(usr.getEmail()).password(usr.getPassword()).authorities("USER").build();
        return user;
    }
}
