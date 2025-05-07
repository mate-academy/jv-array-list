package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        checkSize();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkSize();
        checkIndex(index);
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
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
        checkIndex(index + 1);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T result = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[--size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || (arrayList[i] != null && arrayList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElementException");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] addSize(T[] arrayList) {
        T[] newList = (T[]) new Object[(int) (size * 1.5)];
        System.arraycopy(arrayList, 0, newList, 0, size);
        return newList;
    }

    public void checkSize() {
        if (size == arrayList.length) {
            arrayList = addSize(arrayList);
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBoundsException");
        }
    }
}

