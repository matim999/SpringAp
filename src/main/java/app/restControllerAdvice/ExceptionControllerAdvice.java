package app.restControllerAdvice;

import app.DTO.responseDTO.ResponseMsg;
import app.exceptions.ConflictException;
import app.exceptions.MyAuthenticationServiceException;
import app.exceptions.MyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger logger = Logger.getLogger(ExceptionControllerAdvice.class.getName());

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ResponseMsg> handleNotFoundException(MyNotFoundException ex) {
        logger.info("Exception: MyNotFoundException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseMsg> handleConflictException(ConflictException ex) {
        logger.warning("Exception: ConflictException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MyAuthenticationServiceException.class)
    public ResponseEntity<ResponseMsg> handleMyAuthenticationServiceException(MyAuthenticationServiceException ex) {
        logger.warning("Exception: MyAuthenticationServiceException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseMsg> handleUsernameNotFoundException(ConflictException ex) {
        logger.warning("Exception: UsernameNotFoundException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseMsg> handleAuthenticationException(ConflictException ex) {
        logger.warning("Exception: AuthenticationException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseMsg> handleBadCredentialsException(ConflictException ex) {
        logger.warning("Exception: BadCredentialsException, cause: " + ex.getMessage());
        ResponseMsg responseMsg = new ResponseMsg(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }
}
