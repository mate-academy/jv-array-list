package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final String EXCEPTION_MESSAGE = " index doesn't exist";
    public static final double CAPACITY_INDEX = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        this.arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            resize();
        }

        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index + EXCEPTION_MESSAGE);
        }

        if (size == arrayList.length) {
            resize();
        }

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
        checkIfIndexIsInRange(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexIsInRange(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexIsInRange(index);
        T removedElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && arrayList[i] == null
                    || element != null && element.equals(arrayList[i])) {
                T removedElement = arrayList[i];
                remove(i);
                return removedElement;
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
        return size == 0;
    }

    private void resize() {
        int newCapacity = (int) (arrayList.length * CAPACITY_INDEX);
        T[] newArrayList = (T[]) new Object[newCapacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
        arrayList = newArrayList;
    }

    private void checkIfIndexIsInRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index + EXCEPTION_MESSAGE);
        }
    }
}
