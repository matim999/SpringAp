package app.restControllerAdvice;

import app.DTO.responseDTO.ResponseMsg;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger logger = Logger.getLogger(ExceptionControllerAdvice.class.getName());

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ResponseMsg> handleNotFoundException(MyNotFoundException ex) {
        logger.warning("Exception: MyNotFoundException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseMsg> handleConflictException(ConflictException ex) {
        logger.warning("Exception: ConflictException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.CONFLICT);
    }
}
