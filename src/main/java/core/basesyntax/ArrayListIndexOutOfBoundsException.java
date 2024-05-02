package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    public ArrayListIndexOutOfBoundsException(int index) {
        super("Index out of bounds: " + index);
    }
}
