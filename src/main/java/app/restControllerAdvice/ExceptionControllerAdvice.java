package app.restControllerAdvice;

import app.DTO.ResponseMsg;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ResponseMsg> handleNotFoundException(MyNotFoundException ex){
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseMsg> handleConflictException(ConflictException ex){
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.CONFLICT);
    }
}
