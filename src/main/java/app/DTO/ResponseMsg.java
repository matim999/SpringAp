package app.DTO;

import lombok.Data;

public @Data class ResponseMsg {
    private String message;
    private int status;
    public ResponseMsg(String message, ErrorCode errorCode) {
        this.message = message;
        this.status = errorCode.getCode();
    }
}
