package ramos.jefferson.base.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ramos.jefferson.base.dto.MessageDTO;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(SaveException.class)
    public ResponseEntity handleException(SaveException exception) {
        return throwException(exception);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity handleException(ForbiddenException exception) {
        return throwException(exception);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity handleException(UnauthorizedException exception) {
        return throwException(exception);
    }

    @ExceptionHandler(ResourceNotFounException.class)
    public ResponseEntity handleException(ResourceNotFounException exception) {
        return throwException(exception);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleException(BadRequestException exception) {
        return throwException(exception);
    }
    
    @ExceptionHandler(ServerException.class)
    public ResponseEntity handleException(ServerException exception) {
        return throwException(exception);
    }
    
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity handleException(InvalidTokenException exception) {
        return throwException(exception);
    }

    private ResponseEntity throwException(AbstractBaseException baseException) {
        MessageDTO messageDTO = new MessageDTO(baseException.getHttpStatus().value(), baseException.getMessage());
        return new ResponseEntity<>(messageDTO, baseException.getHttpStatus());
    }
    
}
