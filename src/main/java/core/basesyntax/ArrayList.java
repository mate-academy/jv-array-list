package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    public static final double GROW_INDEX = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkFreeSpace();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element: " + value
                    + " on the position: " + index);
        }
        checkFreeSpace();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index : " + index + " is not valid.");
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't set value. Index "
                    + index + " doesn't exist.");
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element from position: "
                    + index);
        }
        return makeCopyAndDelete(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element || data[i] != null && data[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't remove non-existing element: " + element);
    }

    private void checkFreeSpace() {
        if (size == data.length) {
            data = grow();
        }
    }

    private Object[] grow() {
        int newCapacity = (int) (data.length * GROW_INDEX);
        return data = Arrays.copyOf(data, newCapacity);

    }

    private T makeCopyAndDelete(int index) {
        Object[] elementsToCopy = new Object[(size - 1) - index];
        int indexNextAfterRemoved = index + 1;
        for (int i = 0; i < elementsToCopy.length; i++) {
            elementsToCopy[i] = data[indexNextAfterRemoved++];
        }
        T deletedElement = (T) data[index];
        System.arraycopy(elementsToCopy, 0, data, index, elementsToCopy.length);
        size--;
        return deletedElement;
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
