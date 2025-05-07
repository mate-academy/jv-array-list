package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ELEMENTS = 10;
    private static final Double ARRAY_MULTIPLIER = 1.5;
    private Object[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = new Object[MAX_ELEMENTS];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            grow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (size + 1 == arrayList.length) {
            grow();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, arrayList.length - index - 1);
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
        checkIndex(index);
        T result = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (arrayList[i] == element || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Array list don`t contain " + element);
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
        Object[] resize = new Object[(int) (arrayList.length * ARRAY_MULTIPLIER)];
        System.arraycopy(arrayList, 0, resize, 0, arrayList.length);
        arrayList = resize;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of array`s bounds: " + (size - 1));
        }
    }
}
