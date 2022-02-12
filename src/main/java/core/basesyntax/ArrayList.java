package core.basesyntax;

import java.util.NoSuchElementException;
import static java.util.Arrays.copyOf;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] defaultArray;
    private int size = 0;

    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void grow() {
        if (size == defaultArray.length) {
            defaultArray = copyOf(defaultArray, (int) (size * 1.5));
        }
    }

    @Override
    public void add(T value) {
        grow();
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            grow();
            System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
            defaultArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException
                    ("ArrayList " + index + " OutOfBoundsException");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            defaultArray[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return defaultArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException
                    ("ArrayList " + index + " OutOfBoundsException");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            defaultArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException
                    ("ArrayList " + index + " OutOfBoundsException");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T removedElement = defaultArray[index];
            System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
            size--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException
                    ("ArrayList " + index + " OutOfBoundsException");
        }
    }

    @Override
    public T remove(T element) {
            for (int i = 0; i < size; i++) {
                if (element == defaultArray[i]
                        || (element != null && element.equals(defaultArray[i]))) {
                    return remove(i);
                }
            }
            throw new NoSuchElementException("Element " + element + " doesn`t exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
