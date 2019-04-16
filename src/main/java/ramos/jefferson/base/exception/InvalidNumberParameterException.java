package ramos.jefferson.base.exception;

public class InvalidNumberParameterException extends BadRequestException {

    public InvalidNumberParameterException() {
        super("Invalid parameter: must be a number");
    }

}
