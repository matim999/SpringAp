package app.DTO.responseDTO;

import app.ErrorCode;
import lombok.Data;

public @Data
class ResponseMsg {
    private final String message;
    private final ErrorCode status;
}
