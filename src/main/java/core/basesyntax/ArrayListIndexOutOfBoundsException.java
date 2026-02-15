package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    public ArrayListIndexOutOfBoundsException() {
        super("The index passed to any of the methods is invalid.");
    }
}
