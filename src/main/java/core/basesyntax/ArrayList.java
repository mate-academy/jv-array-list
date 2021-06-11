package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ZERO_INDEX = 0;
    private static final int USUALLY_SIZE = 1;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void checkSize(int length) {
        int capacity = elementData.length;
        while (length + size > capacity) {
            capacity = (int) (capacity * 1.5);
            Object [] inheritElementData = new Object[capacity];
            System.arraycopy(elementData, 0, inheritElementData, 0, size);
            elementData = new Object[capacity];
            elementData = inheritElementData;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < ZERO_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Undefined index");
        }
    }

    private void checkAddIndex(int index) {
        if (index > size || index < ZERO_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Undefined index");
        }
    }

    @Override
    public void add(T value) {
        checkSize(USUALLY_SIZE);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        checkSize(USUALLY_SIZE);
        if (index == size) {
            elementData[size] = value;
        } else if ((index < size) && (index >= ZERO_INDEX)) {
            for (int i = size - 1; i >= index; i--) {
                elementData[i + 1] = elementData[i];
            }
            elementData[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkSize(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
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
        T result = (T) elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        int koeffReWritten = -1;
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element) || ((elementData[i] != null)
                    && (elementData[i].equals(element)))) {
                koeffReWritten = i;
                break;
            }
        }
        if ((koeffReWritten == size - 1) || ((koeffReWritten >= ZERO_INDEX)
                && (koeffReWritten != -1))) {
            remove(koeffReWritten);
            return element;
        } else {
            throw new NoSuchElementException("Can’t find the element " + element
                   + " in the array");
        }
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
