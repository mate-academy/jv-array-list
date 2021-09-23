package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        for (int i = 0; i < size; i++) {
            if (elementData.length == size) {
                elementData = Arrays.copyOf(elementData,
                        elementData.length + (elementData.length >> 1));
            }
        }
        elementData[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            checkLastIndex(index);
        } else {
            checkIndex(index);
        }
        if (size + 1 >= elementData.length) {
            elementData = Arrays.copyOf(elementData,elementData.length + (elementData.length >> 1));
        }
        for (int i = size; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = value;
        size = size + 1;
    }

    @Override
    public void addAll(List<T> list) {
        int oldSize = size;
        size += list.size();
        while (size > elementData.length) {
            elementData = Arrays.copyOf(elementData,elementData.length + (elementData.length >> 1));
        }
        for (int i = oldSize; i < size; i++) {
            elementData[i] = list.get(i - oldSize);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object oldValue = elementData[index];
        size = size - 1;
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        Object oldValue = null;
        int pos = -1;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                oldValue = elementData[i];
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            for (int i = pos; i < size; i++) {
                elementData[i] = elementData[i + 1];
            }
            size -= 1;
        } else {
            throw new NoSuchElementException();
        }
        return (T) oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of range");
        }
    }

    private void checkLastIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of range");
        }
    }
}
