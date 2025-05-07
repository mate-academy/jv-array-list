package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final float SIZE_MULTIPLIER = 1.5f;
    private T[] arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = DEFAULT_SIZE;

    @Override
    public void add(T value) {
        growIfFull();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        growIfFull();
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
        T value = get(index);
        fillEmptyIndex(index);
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || element != null && element.equals(arrayList[i])) {
                fillEmptyIndex(i);
                return element;
            }
        }
        throw new NoSuchElementException("Cannot remove element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, boolean forAdd) {
        if (index < 0 || index >= (forAdd ? size + 1 : size)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void fillEmptyIndex(int index) {
        System.arraycopy(arrayList, index + 1, arrayList, index, size-- - index - 1);
    }

    private void growIfFull() {
        if (size == arrayList.length) {
            int newLength = (int) (arrayList.length * SIZE_MULTIPLIER);
            T[] newList = (T[]) new Object[newLength];
            System.arraycopy(arrayList, 0, newList, 0, size);
            arrayList = newList;
        }
    }
}
