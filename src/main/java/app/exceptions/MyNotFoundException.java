package app.exceptions;

import app.DTO.ErrorCode;

import java.util.function.Supplier;

public class MyNotFoundException extends RuntimeException implements Supplier<MyNotFoundException> {
    private ErrorCode errorCode;
    public MyNotFoundException(String message, ErrorCode errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public MyNotFoundException get() {
        return null;
    }
}
