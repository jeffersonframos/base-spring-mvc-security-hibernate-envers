package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractBaseException extends Exception{
    
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    public AbstractBaseException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
    
}
