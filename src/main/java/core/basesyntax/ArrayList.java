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
        isIndexValid(index, true);
        growIfFull();
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
        if (isIndexValid(index, false)) {
            return arrayList[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (isIndexValid(index, false)) {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (isIndexValid(index, false)) {
            value = arrayList[index];
            fillEmptyIndex(index);
        }
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
        throw new NoSuchElementException("Cannot remove element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isIndexValid(int index, boolean forAdd) {
        if (index >= 0 && index < (forAdd ? size + 1 : size)) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Invalid index");
    }

    private void fillEmptyIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        size--;
    }

    private void growIfFull() {
        if (size == arrayList.length) {
            int newLength = (int) (arrayList.length * SIZE_MULTIPLIER);
            T[] newList = (T[]) new Object[newLength];
            for (int i = 0; i < size; i++) {
                newList[i] = arrayList[i];
            }
            arrayList = newList;
        }
    }
}
