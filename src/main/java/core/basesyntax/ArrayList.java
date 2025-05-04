package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SHOULD_GROW_TIMES = 1.5;
    private int size;
    private Object[] arrayList;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
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
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bound");
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
        checkBoundsIndex(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkBoundsIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        T elementToRemove = get(index);
        System.arraycopy(arrayList, index + 1, arrayList, index, size - 1 - index);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || (arrayList[i] != null
                    && arrayList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElementException");
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
        int newCapacity = (int) (arrayList.length * SHOULD_GROW_TIMES);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(arrayList, 0, newElementData, 0, size);
        arrayList = newElementData;
    }

    private void checkBoundsIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bound");
        }
    }
}
