package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Given index is not valid for size: " + size);
        }
        size++;
        if (size == elementData.length) {
            grow();
        }
        T[] oldElementData = elementData;
        if (size - (index + 1) >= 0) {
            System.arraycopy(oldElementData,
                    index + 1 - 1,
                    elementData,
                    index + 1,
                    size - (index + 1));
        }
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private void grow() {
        T[] oldElementData = elementData;
        elementData = (T[]) new Object[(int)(elementData.length * 1.5f)];
        System.arraycopy(oldElementData,
                0,
                elementData,
                0,
                oldElementData.length);
    }

    @Override
    public T get(int index) {
        checkIndexAvailability(index);
        T result = null;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                result = elementData[i];
            }
        }
        return result;
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Given index is not valid for size: " + size);
        }
        for (int i = 0; i < size; i++) {
            if (i == index) {
                elementData[i] = value;
            }
        }
    }

    @Override
    public T remove(int index) {
        checkIndexAvailability(index);
        T deleted = elementData[index];
        T[] temp = elementData;
        if (size - (index + 1) >= 0) System.arraycopy(temp,
                index + 1,
                elementData,
                index + 1 - 1,
                size - (index + 1));
        size--;
        return deleted;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((Objects.equals(elementData[i], element)) ) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    private void checkIndexAvailability(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Given index is not valid for size: " + size);
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
