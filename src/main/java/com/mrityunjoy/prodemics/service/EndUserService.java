package com.mrityunjoy.prodemics.service;

import com.mrityunjoy.prodemics.exception.BadRequestException;
import com.mrityunjoy.prodemics.model.EndUser;
import com.mrityunjoy.prodemics.model.Role;
import com.mrityunjoy.prodemics.repository.EndUserRepository;
import com.mrityunjoy.prodemics.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndUserService {
    private final EndUserRepository endUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public EndUserService(
            EndUserRepository endUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder
    ) {
        this.endUserRepository = endUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public EndUser createUser(String username, String emailId, String name, String password, String roleName) {
        endUserRepository.findByUsername(username).ifPresent(
                user -> { throw new BadRequestException("Username is not available"); }
        );

        Role role = roleRepository.findByName(roleName).getFirst();
        EndUser user = new EndUser(username, emailId, name, passwordEncoder.encode(password), List.of(role));

        return endUserRepository.save(user);
    }

    @Transactional
    public EndUser setPassword(String username, String password) {
        EndUser user = endUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}
