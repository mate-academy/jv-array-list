package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T [] arrayList = (T[]) new Object[DEFAULT_CAPACITY];

    public void resize() {
        if (arrayList.length == size) {
            int newSize = (int)(arrayList.length * 1.5);
            arrayList = Arrays.copyOf(arrayList, newSize);
        }
    }

    @Override
    public void add(T value) {
        resize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        } else if (index + 1 < size) {
            resize();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            arrayList[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        }
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        T indexOld;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        }
        indexOld = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[size - 1] = null;
        size--;
        return indexOld;
    }

    @Override
    public T remove(T element) {
        T indexOld;
        for (int i = 0; i < arrayList.length; i++) {
            if (Objects.equals(arrayList[i], element)) {
                indexOld = arrayList[i];
                System.arraycopy(arrayList, i + 1, arrayList, i, size - i - 1);
                arrayList[size - 1] = null;
                size--;
                return indexOld;
            }
        }
        throw new NoSuchElementException("Value " + element + " not found.");
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
