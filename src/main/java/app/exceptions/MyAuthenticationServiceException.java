package app.exceptions;

import app.ErrorCode;
import lombok.Getter;

public class MyAuthenticationServiceException extends RuntimeException {
    private @Getter
    ErrorCode errorCode;

    public MyAuthenticationServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
