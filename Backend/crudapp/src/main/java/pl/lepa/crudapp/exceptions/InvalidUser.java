package pl.lepa.crudapp.exceptions;

public class InvalidUser extends RuntimeException {

    public InvalidUser() {
        super();
    }

    public InvalidUser(String message) {
        super(message);
    }

    public InvalidUser(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUser(Throwable cause) {
        super(cause);
    }

    protected InvalidUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
