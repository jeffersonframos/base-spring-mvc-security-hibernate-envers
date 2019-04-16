package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public class ServerException extends AbstractBaseException {
    
    public ServerException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
