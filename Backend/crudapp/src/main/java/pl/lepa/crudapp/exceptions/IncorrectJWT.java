package pl.lepa.crudapp.exceptions;

public class IncorrectJWT extends RuntimeException {
    public IncorrectJWT(String message) {
        super(message);
    }
}
