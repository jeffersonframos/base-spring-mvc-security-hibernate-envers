package ramos.jefferson.base.exception;

import org.springframework.http.HttpStatus;

abstract class BaseException extends Exception{
    
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    public BaseException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
    
}
