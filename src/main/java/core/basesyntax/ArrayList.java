package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] list = (T[]) (new Object[DEFAULT_CAPACITY]);
    private int arraySize = 0;

    @Override
    public void add(T value) {
        list[arraySize++] = value;
        sizeControl();
    }

    @Override
    public void add(T value, int index) {
        if (index <= arraySize && index >= 0) {
            System.arraycopy(list, index, list, index + 1, arraySize - index);
            list[index] = value;
            arraySize++;
            sizeControl();
        } else {
            indexError(index);
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
        indexError(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        indexError(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        indexError(index);
        T removeValue = list[index];
        System.arraycopy(list, index + 1, list, index, arraySize - index);
        arraySize--;
        return removeValue;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < arraySize; i++) {
            if (value == list[i] || value != null && value.equals(list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No found value: " + value);
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private void sizeControl() {
        if (arraySize == list.length) {
            T[] temp = (T[]) new Object[list.length + list.length / 2];
            System.arraycopy(list, 0, temp, 0, arraySize);
            list = temp;
        }
    }

    private void indexError(int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " no found!!!");
        }
    }
}
