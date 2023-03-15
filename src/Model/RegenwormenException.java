package Model;

public class RegenwormenException extends RuntimeException {
    public RegenwormenException(String message) {
        super(message);
    }
    public RegenwormenException(String message, Throwable cause) {
        super(message, cause);
    }
}
