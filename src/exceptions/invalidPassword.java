package exceptions;

public class invalidPassword extends RuntimeException {
    public invalidPassword(String message) {
        super(message);
    }
}
