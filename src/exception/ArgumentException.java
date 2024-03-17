package exception;

/**
 * ошибки с аргументами
 */
public class ArgumentException extends Exception {
    public ArgumentException(String message) {
        super(message);
    }
}
