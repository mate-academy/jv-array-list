package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public ArrayListIndexOutOfBoundsException(String message) {
        super(message);
    }

    public ArrayListIndexOutOfBoundsException(int index) {
        super("Index: " + index);
    }
}
