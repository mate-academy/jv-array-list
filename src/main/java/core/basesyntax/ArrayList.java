package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String EXCEPTION_MESSAGE = "Can't operate with the value, "
            + "index is out of bounds";
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        checkSizeEquality();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
        checkSizeEquality();
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return rearrangeElements(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element
                    || array[i] != null
                    && array[i].equals(element)) {
                return rearrangeElements(i);
            }
        }
        throw new NoSuchElementException("No such element: "
                + element + ", in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray() {
        int newSize = (int) (array.length * GROW_FACTOR);
        array = Arrays.copyOf(array, newSize);
    }

    private T rearrangeElements(int index) {
        T removedElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array [i + 1];
        }
        size--;
        return removedElement;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
    }

    private void checkSizeEquality() {
        if (size == array.length) {
            growArray();
        }
    }
}
