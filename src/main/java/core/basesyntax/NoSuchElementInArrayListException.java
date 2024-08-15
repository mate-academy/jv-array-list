package core.basesyntax;

import java.util.NoSuchElementException;

public class NoSuchElementInArrayListException extends NoSuchElementException {
    public NoSuchElementInArrayListException(String message) {
        super(message);
    }
}
