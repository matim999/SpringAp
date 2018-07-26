package app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.logstash.logback.argument.StructuredArguments.value;

@Component
public class MyOwnAccessDeniedHandler implements AccessDeniedHandler {
    private final static Logger logger = LoggerFactory.getLogger(AccessDeniedHandler.class.getSimpleName() + "Logger");

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            logger.info("{}, for user: {}, on URL: {}", value("action", "Access Denied"),
                    value("user", authentication.getName()), value("url", httpServletRequest.getRequestURI()));
        }
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "No authorities");
    }
}
