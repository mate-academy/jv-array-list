package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    public ArrayListIndexOutOfBoundsException(int index) {
        super(String.format("Index %d is out of array list bounds.", index));
    }
}
