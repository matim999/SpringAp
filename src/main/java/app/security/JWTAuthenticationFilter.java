package app.security;

import app.ErrorCode;
import app.entity.Staff;
import app.exceptions.MyAuthenticationServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

import static app.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Staff creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Staff.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new HashSet<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .claim(AUTHORITIES_KEY, auth.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        if (failed.getClass().isAssignableFrom(InternalAuthenticationServiceException.class)) {
            System.out.println("BAD_CREDENTIAL");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, failed.getMessage());
            response.addHeader("test", "test1");
            response.flushBuffer();
            response.setStatus(500);
            System.out.println("HDSHS");
//            throw new MyAuthenticationServiceException("AGGSG", ErrorCode.AUTHORITIES_SERVER_NOR_RESPONDING);
        } else if (failed.getClass().isAssignableFrom(BadCredentialsException.class)) {
            System.out.println("USER_DISABLED");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, failed.getMessage());
            response.addHeader("test", "test1");
        }
    }


}
