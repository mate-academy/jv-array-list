package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (this.isFull()) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        } else if (isFull()) {
            grow();
        }
        shift(index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (isInvalidValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }

        return (T) elementData[index];

    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (isInvalidValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isInvalidValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        final T element = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
            if (elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element found by provided value");

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return elementData.length == this.size;
    }

    private boolean isInvalidValidIndex(int index) {
        return index < 0 || index > size - 1;
    }

    private void grow() {
        int newCapacity = elementData.length == 1
                ? elementData.length + 1
                : elementData.length + (elementData.length / 2);
        Object [] tempArr = new Object[newCapacity];
        System.arraycopy(elementData, 0, tempArr, 0, elementData.length);
        elementData = tempArr;
    }

    private void shift(int index) {
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }
}
