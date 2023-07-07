package com.streak.authservice;


import com.streak.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userRepository.findByUsername(username);
        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        String passwordHash = userInfo.get().getPasswordHash();

        return User.withUsername(userInfo.get().getUsername())
                                .password("{bcrypt}" + passwordHash)
                                .authorities("app")
                                .build();
    }

}
