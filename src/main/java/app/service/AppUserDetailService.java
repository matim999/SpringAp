package app.service;

import app.entity.Staff;
import app.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

    @Autowired
    private StaffRepository staffRepository;

    public AppUserDetailService() throws NoSuchAlgorithmException {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Staff user = staffRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(messageDigest.(encoder.encode(user.getPassword())))
                .authorities(Collections.emptyList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}