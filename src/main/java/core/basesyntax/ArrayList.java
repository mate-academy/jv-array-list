package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SHOULD_GROW_TIMES = 1.5;
    private static final int STARTING_POSITION = 0;
    private int size;
    private Object[] arrayList;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            resizeArrayList();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of array size");
        }
        if (size == arrayList.length) {
            resizeArrayList();
        }
        modifiedArrayByIndex(index);
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
        indexOutOfSize(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        indexOutOfSize(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        removeByIndex(index);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        int index = indexOf(t);
        indexOutOfSize(index);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] resizeArrayList() {
        int newCapacity = (int) (arrayList.length * SHOULD_GROW_TIMES);
        Object[] increasedArray = new Object[newCapacity];
        System.arraycopy(arrayList,
                STARTING_POSITION,
                increasedArray,
                STARTING_POSITION,
                size);
        return arrayList = increasedArray;
    }

    private void modifiedArrayByIndex(int index) {
        System.arraycopy(arrayList,
                index,
                arrayList,
                index + 1,
                size - index);

    }

    private void removeByIndex(int index) {
        System.arraycopy(arrayList,
                index + 1,
                arrayList,
                index,
                size - 1);
    }

    private int indexOf(T t) {
        for (int i = 0; i < size; i++) {
            if (t != null ? t.equals(arrayList[i]) : arrayList[i] == t) {
                return i;
            }
        }
        return -1;
    }

    private void indexOutOfSize(int index) {
        if (index == -1) {
            throw new NoSuchElementException("Can't remove element");
        }
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of array size");
        }
    }
}
