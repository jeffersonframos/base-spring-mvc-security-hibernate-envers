package ramos.jefferson.base.exception;

public class NotSameEntityException extends BadRequestException {

    public NotSameEntityException() {
        super("Must be the same object");
    }

}
