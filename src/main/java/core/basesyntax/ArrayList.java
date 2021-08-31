package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] object;
    private int size;

    public ArrayList() {
        object = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        increaseListSize();
        object[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        increaseListSize();
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
        indexValidation(index);
        return object[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        object[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T currentObject = object[index];
        System.arraycopy(object, index + 1,
                object, index, size - index - 1);
        object[--size] = null;
        return currentObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (object[i] == element || object[i] != null && object[i].equals(element)) {
                return remove(i);
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
            T[] valuesTemp = (T[]) new Object[object.length + object.length / 2];
            System.arraycopy(object, 0, valuesTemp, 0, size);
            object = valuesTemp;
        }
    }

    private void indexValidation(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
