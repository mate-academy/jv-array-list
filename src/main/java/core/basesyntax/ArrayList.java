package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        this.elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            grow();
        }
        checkArrayListIndexOutOfBoundsExceptionForAdd(index);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        this.elementData[index] = value;
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
        checkArrayListIndexOutOfBoundsException(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkArrayListIndexOutOfBoundsException(index);
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkArrayListIndexOutOfBoundsException(index);
        T value = elementData[index];
        if (index + 1 != size) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        }
        size -= 1;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                System.arraycopy(elementData, i + 1, elementData, i, size - 1 - i);
                size -= 1;
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size != 0) {
            return false;
        }
        return true;
    }

    private T[] grow() {
        return elementData = Arrays.copyOf(elementData, (int) (elementData.length * 1.5));
    }

    private void checkArrayListIndexOutOfBoundsException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds size " + size);
        }
    }

    private void checkArrayListIndexOutOfBoundsExceptionForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds size " + size);
        }
    }
}
