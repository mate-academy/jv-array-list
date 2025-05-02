package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            arrayList = extend();
        }
        this.arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkRangeToAdd(index);
        if (size == arrayList.length) {
            arrayList = extend();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int additionalLength = list.size();
        if (size + additionalLength > arrayList.length) {
            arrayList = extend(size + additionalLength);
        }
        if (additionalLength != 0) {
            System.arraycopy(list.toArray(), 0, arrayList, size, additionalLength);
            size += additionalLength;
        }
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        final T previousValue = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, (size - 1) - index);
        arrayList[size - 1] = null;
        size--;
        return previousValue;
    }

    @Override
    public T remove(T element) {
        int index = findElementIndex(element);
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

    private T[] extend() {
        return extend(size + 1);
    }

    private T[] extend(int minCapacity) {
        int capacity = arrayList.length;
        while (capacity < minCapacity) {
            capacity += capacity / 2;
        }
        Object[] copy = new Object[capacity];
        System.arraycopy(arrayList, 0, copy, 0, Math.min(arrayList.length, capacity));
        return (T[]) copy;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " out of bound");
        }
    }

    private void checkRangeToAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                                            + " out of bounds of addition");
        }
    }

    private int findElementIndex(T element) {
        boolean isFind = false;
        int i = 0;
        if (element == null) {
            for (; i < size; i++) {
                if (arrayList[i] == null) {
                    isFind = true;
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (element.equals(arrayList[i])) {
                    isFind = true;
                    break;
                }
            }
        }
        if (!isFind) {
            throw new NoSuchElementException("Element: " + element + " is not exist");
        }
        return i;
    }

    @Override
    public T[] toArray() {
        return this.arrayList;
    }
}
