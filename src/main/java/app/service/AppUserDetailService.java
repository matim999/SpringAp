package app.service;

import app.ErrorCode;
import app.entity.RolesList;
import app.entity.Staff;
import app.exceptions.MyAuthenticationServiceException;
import app.repository.StaffRepository;
import app.security.MyErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;

import static app.ErrorCode.AUTHORITIES_SERVER_NOR_RESPONDING;

@Service
public class AppUserDetailService implements UserDetailsService {
    private final StaffRepository staffRepository;
    @Value("${authoritiesServiceUrl}")
    public String AUTHORITIES_URL;

    @Autowired
    public AppUserDetailService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Staff user = staffRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
        RolesList rolesList = getRolesFromOtherService(user);
        Set<GrantedAuthority> grantedAuthorities = rolesList.getList().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toSet());
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private RolesList getRolesFromOtherService(Staff user) {
        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.setErrorHandler(new MyErrorHandler());
            String authUrl = AUTHORITIES_URL + user.getStaffId();
            RolesList rolesList = restTemplate.getForObject(authUrl, RolesList.class);
            return rolesList;

    }

}