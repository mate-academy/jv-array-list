package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    public ArrayListIndexOutOfBoundsException(String message, RuntimeException cause) {
        super(message, cause);
        System.out.println(message + ": " + cause);
    }
}
