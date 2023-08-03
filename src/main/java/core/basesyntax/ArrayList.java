package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;
    private int capacity;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            this.grow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            this.add(value);
        } else {
            this.checkIndex(index);
            this.add(arrayList[size - 1]);
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index - 2);
            arrayList[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                this.add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        this.checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        this.checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        this.checkIndex(index);
        final T element = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        arrayList[size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == null && element == null
                    || arrayList[i] != null && arrayList[i].equals(element)) {
                return this.remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
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
        capacity += capacity >> 1;
        T[] newArrayList = (T[]) new Object[capacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        arrayList = newArrayList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index passed is invalid");
        }
    }
}
