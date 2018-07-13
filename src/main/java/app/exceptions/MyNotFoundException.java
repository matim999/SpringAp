package app.exceptions;

import app.ErrorCode;
import lombok.Getter;

public class MyNotFoundException extends RuntimeException {
    private @Getter
    ErrorCode errorCode;

    public MyNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
