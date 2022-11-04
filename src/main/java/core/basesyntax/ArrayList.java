package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        arrayList = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            grow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == capacity) {
            grow();
        }
        for (int i = size; i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }
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
        if (isIndexOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (isIndexOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        final T removedElement = arrayList[index];
        for (int i = index; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        size--;
        arrayList[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(arrayList[i], element)) {
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
        return size == 0;
    }

    private void grow() {
        capacity = size * 15 / 10;
        T[] newArrayList = (T[]) new Object[capacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        arrayList = newArrayList;
    }

    private boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    private boolean isEqual(T elementOne, T elementTwo) {
        return elementOne == elementTwo
                || elementOne != null && elementOne.equals(elementTwo);
    }
}
