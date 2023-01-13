package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY_DEFAULT = 10;
    private static final double CAPACITY_GROW = 1.5;
    private int size;
    private T[] arrayList;

    public ArrayList() {
        arrayList = (T[]) new Object[CAPACITY_DEFAULT];
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
        if (size == arrayList.length) {
            grow();
        }
        System.arraycopy(arrayList, index,arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
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
        final T removedArrayList = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - 1 - index);
        size--;
        return removedArrayList;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            if (arrayList[i] == element || arrayList[i] != null && arrayList[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(" The list is empty");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index is invalid");
        }
    }

    private void grow() {
        T[] arrayListCopy = (T[]) new Object[(int)(arrayList.length * CAPACITY_GROW)];
        System.arraycopy(arrayList, 0, arrayListCopy, 0, arrayList.length);
        arrayList = arrayListCopy;
    }
}
