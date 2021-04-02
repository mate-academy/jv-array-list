package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    public ArrayListIndexOutOfBoundsException(String message) {
        super(message);
    }

    public ArrayListIndexOutOfBoundsException(String message, IndexOutOfBoundsException e) {
        super(message);
    }
}
