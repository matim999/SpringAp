package app.exceptions;

import app.DTO.ErrorCode;

public class MyNotFoundException extends RuntimeException{
    private ErrorCode errorCode;
    public MyNotFoundException(String message, ErrorCode errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
