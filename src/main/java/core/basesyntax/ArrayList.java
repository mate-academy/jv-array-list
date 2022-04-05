package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        T[] data = elementData;
        elementData = (T[]) new Object[(int) (elementData.length * 1.5)];
        System.arraycopy(data, 0, elementData, 0, size);
    }

    private boolean checkIndex(int index) {
        return index < size && index >= 0;
    }

    private void copyAndRemove(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        if (size == elementData.length) {
            grow();
        }
        if (checkIndex(index)) {
            System.arraycopy(elementData, index,
                    elementData, index + 1, size - index);
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't add element by index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't get element by index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't set element by index");
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            T objectRemoved = elementData[index];
            copyAndRemove(index);
            return objectRemoved;
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index isn't exist");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                copyAndRemove(i);
                return element;
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
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
