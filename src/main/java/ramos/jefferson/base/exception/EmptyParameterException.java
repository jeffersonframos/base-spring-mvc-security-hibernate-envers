package ramos.jefferson.base.exception;

public class EmptyParameterException extends BadRequestException {
    
    public EmptyParameterException(String parameterName) {
        super(parameterName + " must not be empty");
    }
    
}
