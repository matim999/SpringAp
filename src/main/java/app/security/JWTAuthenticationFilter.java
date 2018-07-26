package app.security;

import app.Markers;
import app.entity.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
import static net.logstash.logback.argument.StructuredArguments.value;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticationFilter.class.getSimpleName() + "Logger");
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
            logger.info(Markers.loginMarker, "{} for username: \"{}\"", value("action", "Attempt Login"),
                    value("username", creds.getUsername()));
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
        logger.info(Markers.loginMarker, "{} for username: \"{}\"", value("action", "Success Login"),
                value("username", ((User) auth.getPrincipal()).getUsername()));
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
        if (failed.getClass().isAssignableFrom(BadCredentialsException.class)) {
            response.addHeader("Error", "Bad Credentials");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, failed.getMessage());
            logger.info(Markers.loginMarker, "{}, result: {}, reason: {}", value("action", "Attempt Login"),
                    value("result", "Failed"), value("reason", failed.getMessage()));
        } else if (failed.getClass().isAssignableFrom(InternalAuthenticationServiceException.class)) {
            response.addHeader("Error", "Internal Authentication Service Error");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, failed.getMessage());
            logger.info(Markers.loginMarker, "{}, result: {}, reason: {}", value("action", "Attempt Login"),
                    value("result", "Failed"), value("reason", "Internal Authentication Service Error"));
        }
    }

}
