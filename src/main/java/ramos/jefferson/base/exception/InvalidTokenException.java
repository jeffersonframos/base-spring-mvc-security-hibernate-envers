package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends AbstractBaseException {
    
    public InvalidTokenException() {
        super("Invalid access token", HttpStatus.UNAUTHORIZED);
    }

}
