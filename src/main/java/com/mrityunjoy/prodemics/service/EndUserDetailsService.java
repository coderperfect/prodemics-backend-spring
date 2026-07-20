package com.mrityunjoy.prodemics.service;

import com.mrityunjoy.prodemics.model.EndUser;
import com.mrityunjoy.prodemics.model.Role;
import com.mrityunjoy.prodemics.repository.EndUserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EndUserDetailsService implements UserDetailsService {

    private final EndUserRepository repository;

    public EndUserDetailsService(EndUserRepository repository) {
        this.repository = repository;
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        EndUser user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        String[] authorities = user.getRoles().stream().map(Role::getName).toArray(String[]::new);

        return User.builder().username(user.getUsername()).password(user.getPassword()).authorities(authorities)
                .build();
    }
}
