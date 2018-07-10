package app.DTO;

import lombok.Data;

public @Data
class ResponseMsg {
    private final String message;
    private final ErrorCode status;
}
