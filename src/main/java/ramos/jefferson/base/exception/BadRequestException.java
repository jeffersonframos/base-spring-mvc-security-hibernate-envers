package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    
    public BadRequestException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
    
}
