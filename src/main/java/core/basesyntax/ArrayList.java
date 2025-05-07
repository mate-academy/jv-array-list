package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
        grow();
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
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        T number = get(index);
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return number;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayList.length; i++) {
            if (element == arrayList[i]
                    || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Elements do not exist" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == arrayList.length) {
            int newSize = (int) (arrayList.length * 1.5);
            T[] newArrayList = (T[]) new Object[newSize];
            System.arraycopy(arrayList, 0, newArrayList, 0, size);
            arrayList = newArrayList;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }
}

