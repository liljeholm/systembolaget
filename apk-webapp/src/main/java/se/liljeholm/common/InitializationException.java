package se.liljeholm.common;

/**
 * @author torbjorn
 *
 */
public class InitializationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InitializationException(String message, Exception cause) {
        super(message, cause);
    }
}
