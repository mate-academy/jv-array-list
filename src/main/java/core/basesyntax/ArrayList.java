package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementArray;
    private int size;

    public ArrayList() {
        this.elementArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elementArray.length == size) {
            grow();
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (elementArray.length == size) {
            grow();
        }
        checkIndexForAdd(index);
        System.arraycopy(elementArray, index, elementArray, index + 1, size - index);
        elementArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (size == elementArray.length) {
            grow();
        }
        final T removeElement = (T) elementArray[index];
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementArray[i] == null && element == null
                    || elementArray[i] != null && elementArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void setElementArray(Object[] elementArray) {
        this.elementArray = elementArray;
    }

    private Object[] grow() {
        int newLength = size * 3 / 2;
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementArray, 0, newArray, 0, size);
        return elementArray = newArray;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
    }
}
