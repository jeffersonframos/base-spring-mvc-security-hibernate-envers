package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public abstract class ResourceNotFounException extends AbstractBaseException{
    
    public ResourceNotFounException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
    
}
