package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int startLength = 10;
    private T[] list = (T[]) (new Object[startLength]);
    private int arraySize = 0;

    @Override
    public void add(T value) {
        list[arraySize] = value;
        arraySize++;
        sizeControl();
        return;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size() && index >= 0) {
            T temp = list[index];
            list[index] = value;
            for (int i = (index + 1); i < list.length; i++) {
                value = list[i];
                list[i] = temp;
                temp = value;
            }
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
        for (int i = index; i < (list.length - 1); i++) {
            list[i] = list[i + 1];
        }
        arraySize--;
        return removeValue;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < arraySize; i++) {
            if (value != null && value.equals(list[i])) {
                T removeValue = list[i];
                remove(i);
                return removeValue;
            }
            if (value == null && value == list[i]) {
                remove(i);
                return null;
            }
        }
        throw new NoSuchElementException("No found!!!");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        if (arraySize == 0) {
            return true;
        }
        return false;
    }

    private void sizeControl() {
        if (arraySize == list.length) {
            T[] temp = (T[]) new Object[list.length + list.length / 2];
            for (int i = 0; i < list.length; i++) {
                temp[i] = list[i];
            }
            list = temp;
        }
    }

    private void indexError(int index) {
        if (index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index error!!!");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is negative!!!");
        }
    }
}
