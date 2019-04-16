package ramos.jefferson.base.exception;

public class InvalidPageRequestParameterException extends BadRequestException {

    public InvalidPageRequestParameterException() {
        super("The page and/or quantity parameters must be greater than zero(0)");
    }

}
