package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AbstractBaseException{
    
    public UnauthorizedException(String message){
        super(message, HttpStatus.UNAUTHORIZED);
    }
    
}
