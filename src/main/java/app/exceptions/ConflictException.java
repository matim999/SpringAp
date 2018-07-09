package app.exceptions;

import app.DTO.ErrorCode;

public class ConflictException extends RuntimeException {
    private ErrorCode errorCode;
    public ConflictException(String message, ErrorCode errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
