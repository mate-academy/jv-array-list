package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final float INCREASE_RATE = 1.5f;
    private Object[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = new Object[START_CAPACITY];
        size = 0;
    }

    private void grow() {
        Object[] grownArray = new Object[(int)(arrayList.length * INCREASE_RATE)];
        System.arraycopy(arrayList, 0, grownArray,
                0, arrayList.length);
        arrayList = grownArray;
    }

    private void expandArray(int size) {
        if (size >= arrayList.length) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index exceeded the array!");
        }
    }

    @Override
    public void add(T value) {
        expandArray(size);
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index exceeded the array!");
        }
        expandArray(size);
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
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index exceeded the array!");
        }
        final T removedValue = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && arrayList[i] == null
                    || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element!");
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
