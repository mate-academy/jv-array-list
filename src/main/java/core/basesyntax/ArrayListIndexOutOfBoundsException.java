package core.basesyntax;

public class ArrayListIndexOutOfBoundsException extends RuntimeException {
    
    public ArrayListIndexOutOfBoundsException() {
        super("Index out of bounds");
    }
    
    public ArrayListIndexOutOfBoundsException(String message) {
        super("ArrayList index error: " + message);
    }
}
