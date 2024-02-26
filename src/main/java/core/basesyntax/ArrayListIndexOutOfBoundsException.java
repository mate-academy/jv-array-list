package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    public ArrayListIndexOutOfBoundsException(int index, int size) {
        super("Index: " + index + "; size: " + size);
    }
}
