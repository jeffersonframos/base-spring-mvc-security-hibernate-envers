package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException{
    
    public ForbiddenException(String message){
        super(message, HttpStatus.FORBIDDEN);
    }
    
}
