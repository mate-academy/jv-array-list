package core.basesyntax;

import java.util.NoSuchElementException;

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
        if (index != size) {
            indexValidation(index);
        }
        resize();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
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
            return remove(index);
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
            T[] newValues = (T[]) new Object[size + (size >> 1)];
            System.arraycopy(elementData, 0, newValues, 0, size);
            elementData = newValues;
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if ((value == elementData[i]) || (value != null && value.equals(elementData[i]))) {
                return i;
            }
        }
        return -1;
    }

    private boolean indexValidation(int index) {
        if (index >= size || index < 0) {
            throw new
                    ArrayListIndexOutOfBoundsException(
                    "The index that you specified is not within bounds.");
        }
        if (index < size && index >= 0) {
            return true;
        }
        return false;
    }
}
