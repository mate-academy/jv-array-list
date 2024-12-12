package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private Object[] array = new Object[10];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size + 1 == array.length) {
            Object[] newArray = new Object[array.length + array.length / 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        if (size + 1 >= array.length) {
            Object[] newArray = new Object[array.length + array.length / 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        System.arraycopy(array, index, array, index + 1, size() - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int summarySize = list.size() + size();
        final int tempSize = summarySize;
        summarySize = summarySize + summarySize / 2;
        Object[] newArray = new Object[summarySize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
        for (int i = 0; i < list.size(); i++) {
            array[i + size] = list.get(i);
        }
        size = tempSize;
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        T result = (T) array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(get(i), element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
