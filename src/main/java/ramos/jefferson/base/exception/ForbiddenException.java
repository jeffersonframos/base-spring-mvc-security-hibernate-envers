package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends AbstractBaseException{
    
    public ForbiddenException(String message){
        super(message, HttpStatus.FORBIDDEN);
    }
    
}
