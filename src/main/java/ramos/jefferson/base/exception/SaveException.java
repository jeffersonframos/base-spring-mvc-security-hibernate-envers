package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public class SaveException extends BaseException{
    
    public SaveException(String message){
        super(message, HttpStatus.CONFLICT);
    }
    
}
