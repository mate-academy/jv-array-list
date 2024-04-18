package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;
    private int capacity;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void growCapacity() {
        if (isEmpty()) {
            arrayList = (T[]) new Object[DEFAULT_CAPACITY];
            capacity = DEFAULT_CAPACITY;
        }
        T[] initialArray = arrayList;
        arrayList = (T[]) new Object[capacity + capacity / 2];
        System.arraycopy(initialArray, 0, arrayList, 0, size);
        capacity = arrayList.length;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            growCapacity();
        }
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
        if (size == capacity) {
            growCapacity();
        }
        if (index == size) {
            add(value);
        } else {
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
            arrayList[index] = value;
            size++;
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
        indexCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T arrayElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[size--] = null;
        return arrayElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arrayList[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
