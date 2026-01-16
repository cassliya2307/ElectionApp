package exceptions;

public class electionIsNotActiveException extends RuntimeException {
    public electionIsNotActiveException(String message) {
        super(message);
    }
}
