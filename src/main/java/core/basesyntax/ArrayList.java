package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resizeIfArrayIsFull() {
        if (arrayList.length == size) {
            T[] newArrayList = (T[]) new Object[(int) (arrayList.length * CAPACITY_MULTIPLIER)];
            System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
            arrayList = newArrayList;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index
                    + ". Index should be inclusively between 0 and " + size);
        }
    }

    private void checkIndexAddByIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index
                    + ". Index should be negative and not greater than " + size);
        }
    }

    @Override
    public void add(T value) {
        resizeIfArrayIsFull();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAddByIndex(index);
        resizeIfArrayIsFull();
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
        checkIndex(index);
        T removedValue = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - size);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i] || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in the list: " + element);
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
