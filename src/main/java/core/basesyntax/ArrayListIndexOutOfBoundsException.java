package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public ArrayListIndexOutOfBoundsException(int index, int size) {
        super("Index " + index + " out of bounds for size " + size);
    }
}
