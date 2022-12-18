package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;
    private int arraySize = DEFAULT_CAPACITY;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            resize();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        } else {
            throw new
                    ArrayListIndexOutOfBoundsException(
                    "The index that you specified is not within bounds.");
        }
    }

    @Override
    public void addAll(List<T> list) {
        resize();
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
            resize();
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return elementData[index];
        } else {
            throw new
                    ArrayListIndexOutOfBoundsException(
                    "The index that you specified is not within bounds.");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new
                    ArrayListIndexOutOfBoundsException(
                    "The index that you specified is not within bounds.");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new
                    ArrayListIndexOutOfBoundsException(
                    "The index that you specified is not within bounds.");
        }
        final T oldObject = elementData[index];
        System.arraycopy(
                elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        resize();
        return oldObject;
    }

    @Override
    public T remove(T element) {
        if (element != null) {
            int index = indexOf(element);
            if (index == -1) {
                throw new NoSuchElementException(
                        "Can't remove non-existing element: " + element);
            }

            final T oldValue = elementData[index];
            System.arraycopy(
                    elementData, index + 1, elementData, index, size - index - 1);
            elementData[size] = null;
            size--;
            resize();
            return oldValue;
        }
        size--;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size >= arraySize) {
            T[] newValues = (T[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elementData, 0, newValues, 0, size);
            elementData = newValues;
        }
        if (size >= DEFAULT_CAPACITY && size < arraySize / 4) {
            T[] newValues = (T[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elementData, 0, newValues, 0, size);
            elementData = newValues;
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, elementData[i])) {
                return i;
            }
        }
        return -1;
    }
}
