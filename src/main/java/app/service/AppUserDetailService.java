package app.service;

import app.entity.Staff;
import app.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.*;

import java.security.NoSuchAlgorithmException;

import static java.util.Collections.emptyList;

@Service
public class AppUserDetailService implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StaffRepository staffRepository;

    @Autowired
    public AppUserDetailService(BCryptPasswordEncoder bCryptPasswordEncoder, StaffRepository staffRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Staff user = staffRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));

        return new User(user.getUsername(), user.getPassword(), emptyList());
    }

}