package app.exceptions;

import app.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationServiceException extends AuthenticationException {
    private @Getter
    ErrorCode errorCode;

    public MyAuthenticationServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
