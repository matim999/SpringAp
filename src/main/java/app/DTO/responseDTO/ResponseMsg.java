package app.DTO.responseDTO;

import app.ErrorCode;
import lombok.Data;

public @Data
class ResponseMsg {
    private final String message;
    private final int errorCode;

    public ResponseMsg(String message, ErrorCode status) {
        this.message = message;
        this.errorCode = status.getCode();
    }
}
