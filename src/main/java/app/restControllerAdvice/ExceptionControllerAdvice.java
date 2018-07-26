package app.restControllerAdvice;

import app.DTO.responseDTO.ResponseMsg;
import app.exceptions.ConflictException;
import app.exceptions.MyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static net.logstash.logback.argument.StructuredArguments.value;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class.getName() + "Logger");

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ResponseMsg> handleNotFoundException(MyNotFoundException ex) {
        logger.warn("{}: type: {}, cause: {} {}", value("action", "Exception"),
                value("type", "NotFoundException"), value("cause", ex.getMessage()), keyValue("errorCode", ex.getErrorCode().getCode()));
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseMsg> handleConflictException(ConflictException ex) {
        logger.warn("{}: type: {}, cause: {} {}", value("action", "Exception"),
                value("type", "ConflictException"), value("cause", ex.getMessage()), keyValue("errorCode", ex.getErrorCode().getCode()));
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.CONFLICT);
    }
}
