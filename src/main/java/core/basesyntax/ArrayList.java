package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] object;
    private int size = 0;

    public ArrayList() {
        object = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == object.length) {
            increaseListSize();
        }
        object[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index exceeds size of the list");
        }
        if (size == object.length) {
            increaseListSize();
        }
        System.arraycopy(object, index, object, index + 1, size - index);
        object[index] = value;
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
        if (index < size && index >= 0) {
            return object[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index passed is invalid");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            object[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index passed is invalid");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T currentObject = object[index];
            System.arraycopy(object, index + 1, object, index, size - index - 1);
            object[--size] = null;
            return currentObject;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index passed is invalid");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, object[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseListSize() {
        if (size == object.length) {
            Object[] valuesTemp = new Object[object.length + object.length / 2];
            System.arraycopy(object, 0, valuesTemp, 0, size);
            object = (T[]) valuesTemp;
        }
    }
}
