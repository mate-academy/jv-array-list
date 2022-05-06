package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_SIZE = 10;
    private static final double INCREMENT = 1.5;
    private Object[] baseObjects;
    private int size;

    public ArrayList() {
        baseObjects = new Object[START_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == baseObjects.length) {
            resize();
        }
        baseObjects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != 0) {
            isInRange(index - 1, "Cannot use add for index ");
        }
        if (size == baseObjects.length) {
            resize();
        }
        System.arraycopy(baseObjects, index, baseObjects, index + 1, size - index);
        baseObjects[index] = value;
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
        isInRange(index, "Cannot use get for index ");
        return (T) baseObjects[index];
    }

    @Override
    public void set(T value, int index) {
        isInRange(index, "Cannot use set for index ");
        baseObjects[index] = value;
    }

    @Override
    public T remove(int index) {
        isInRange(index, "Cannot use remove for index ");
        T element = (T) baseObjects[index];
        System.arraycopy(baseObjects, index + 1, baseObjects, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(baseObjects[i], element)) {
                System.arraycopy(baseObjects, i + 1, baseObjects, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isInRange(int index, String message) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(message + index);
        }
    }

    private void resize() {
        int newCapacity = (int) (baseObjects.length * INCREMENT);
        Object[] newObjects = new Object[newCapacity];
        System.arraycopy(baseObjects, 0, newObjects, 0, baseObjects.length);
        baseObjects = newObjects;
    }
}
