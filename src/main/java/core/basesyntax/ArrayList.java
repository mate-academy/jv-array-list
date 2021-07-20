package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLICATION = 1.5;

    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getIndex(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == value || value != null && value.equals(arrayList[i])) {
                return i;
            }
        }
        return index;
    }

    @Override
    public void add(T value) {
        resize(size + 1);
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        resize(size + 1);

        T[] newList = (T[]) new Object[(int) (arrayList.length)];
        // adding at particular position is made in a few steps
        // step 1
        int startSrc = 0;
        int startDest = 0;
        int count = index;
        System.arraycopy(arrayList, startSrc, newList, startDest, count);
        newList[index] = value;

        // step 2
        startSrc = index;
        startDest += count + 1;
        count = size - count;
        System.arraycopy(arrayList, startSrc, newList, startDest, count);

        arrayList = newList;
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
        checkIndex(index, false);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T[] newList = (T[]) new Object[(int) (arrayList.length)];
        // extraction is made in two steps
        // step 1
        int startSrc = 0;
        int startDest = 0;
        int count = index;
        System.arraycopy(arrayList, startSrc, newList, startDest, count);

        // step 2
        startSrc = index + 1;
        startDest += count;
        count = size - count - 1;
        System.arraycopy(arrayList, startSrc, newList, startDest, count);

        T removed = get(index);
        arrayList = newList;
        size--;

        return removed;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list!");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public String toString() {

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < size; i++) {
            b.append(String.valueOf(arrayList[i]));
            if (i == (size - 1)) {
                b.append(']');
            } else {
                b.append(", ");
            }
        }

        return "ArrayList = " + b.toString();
    }

    private void checkIndex(int index, boolean isAdd) {
        if (index >= 0 && index < (size + (isAdd ? 1 : 0))) {
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("IndexOutOfBounds: " + index
                + " is out of the ranges");
    }

    private void resize(int newSize) {
        if (newSize <= arrayList.length) {
            return;
        }

        T[] newList = (T[]) new Object[(int) (arrayList.length * MULTIPLICATION)];
        System.arraycopy(arrayList, 0, newList, 0, arrayList.length);
        arrayList = newList;
        resize(newSize);
    }
}
