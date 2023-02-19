package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index != size) {
            indexCheck(index);
            System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
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
        T element = arrayList[index];
        size--;
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i] || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(T element) {
        return indexOfRange(element, size);
    }

    private int indexOfRange(T element, int end) {
        if (element == null) {
            for (int i = 0; i < end; i++) {
                if (arrayList[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < end; i++) {
                if (element.equals(arrayList[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private void grow() {
        if (size == arrayList.length) {
            int newCapacity = arrayList.length + (arrayList.length >> 1);
            Object[] newList = new Object[newCapacity];
            System.arraycopy(arrayList, 0, newList, 0, size);
            arrayList = (T[]) newList;
        }
    }
}
