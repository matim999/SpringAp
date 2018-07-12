package app.exceptions;

import app.ErrorCode;
import lombok.Getter;

public class ConflictException extends RuntimeException {
    private @Getter ErrorCode errorCode;
    public ConflictException(String message, ErrorCode errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
