package exceptions;

public class tooYoungToVote extends RuntimeException {
    public tooYoungToVote(String message) {
        super(message);
    }
}
