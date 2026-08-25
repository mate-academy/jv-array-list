package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;
    private Object[] arrayList = new Object[INITIAL_CAPACITY];
    private int size = 0;

    private void outOfBoundsCheck(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Given index must be equal or less than ArrayList's size (positive)");
        }
    }

    private void outOfBoundsPlusCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Given index must be equal or less than ArrayList's size (positive)");
        }
    }

    private void resizeCheck() {
        Object[] newArrayList;
        if (size == arrayList.length) {
            newArrayList = new Object[arrayList.length + (arrayList.length >> 1)];
            System.arraycopy(arrayList, 0, newArrayList, 0, arrayList.length);
            arrayList = newArrayList;
        }
    }

    @Override
    public void add(T value) {
        resizeCheck();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        outOfBoundsCheck(index);
        resizeCheck();
        if (index != size) {
            for (int i = size; i > index; i--) {
                arrayList[i] = arrayList[i - 1];
            }
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newArrayList = new Object[arrayList.length];
        while (size + list.size() > newArrayList.length) {
            newArrayList = new Object[newArrayList.length << 1];
        }
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        for (int i = 0; i < list.size(); i++) {
            newArrayList[i + size] = list.get(i);
        }
        arrayList = newArrayList;
        size += list.size();
    }

    @Override
    public T get(int index) {
        outOfBoundsPlusCheck(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsPlusCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        outOfBoundsPlusCheck(index);
        final T removedElement = (T) arrayList[index];
        for (int i = index; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        size--;
        arrayList[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i <= size; i++) {
            if (arrayList[i] == null ? element == null : arrayList[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("The element was not found, check again");
        }
        final T removedElement = (T) arrayList[index];
        for (int i = index; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        size--;
        arrayList[size] = null;
        return removedElement;
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
