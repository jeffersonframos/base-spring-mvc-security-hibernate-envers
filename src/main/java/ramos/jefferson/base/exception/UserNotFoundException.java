package ramos.jefferson.base.exception;

public class UserNotFoundException extends ResourceNotFounException {

    public UserNotFoundException() {
        super("User not found");
    }

}
