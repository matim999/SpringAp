package app.DTO;

public class ResponseMsg {
    private String message;
    private int status;
    public ResponseMsg(String message, ErrorCode errorCode) {
        this.message = message;
        this.status = errorCode.getCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(ErrorCode status) {
        this.status = status.getCode();
    }
}
