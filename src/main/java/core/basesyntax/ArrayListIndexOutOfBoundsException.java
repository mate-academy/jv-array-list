package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    public ArrayListIndexOutOfBoundsException(int size) {
        super("Index should be in range from 0 to "
                + size);
    }
}
