package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_INDEX = 1.5;
    private static final int ONE = 1;
    private int size;

    private Object[] arrayList;

    public ArrayList() {
        this.arrayList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (arrayList.length == size) {
            resizeArrayList();
        }
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Element is not exists by index " + index);
        }
        if (arrayList.length == size) {
            resizeArrayList();
        }
        System.arraycopy(arrayList, index, arrayList, index + ONE, size - index);
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
        size--;
        T value = (T) arrayList[index];
        System.arraycopy(arrayList, index + ONE, arrayList, index, size - index);
        return value;
    }

    @Override
    public T remove(T element) {
        T value;
        for (int i = 0; i < size; i++) {
            if (equalsObjects(arrayList[i], element)) {
                value = (T) arrayList[i];
                remove(i);
                return value;
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

    private void resizeArrayList() {
        int newSize = (int) (arrayList.length + arrayList.length * GROW_INDEX);
        T[] newSizeArrayList = (T[]) new Object[newSize];
        System.arraycopy(arrayList, 0, newSizeArrayList, 0, size);
        arrayList = newSizeArrayList;
    }
    
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element is not exists by index " + index);
        }
    }

    private boolean equalsObjects(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
