package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        reviewSize();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        reviewSize();
        addingShift(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        T nedded = get(index);
        removingShift(index);
        return nedded;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < arrayList.length; i++) {
            if (t == arrayList[i] || (t != null && t.equals(arrayList[i]))) {
                return remove(i);
            }
        }
        throw new java.util.NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void reviewSize() {
        if (size == arrayList.length) {
            int newSize = (int) (arrayList.length * 1.5);
            arrayList = Arrays.copyOf(arrayList, newSize);
        }
    }

    private void removingShift(int index) {
        System.arraycopy(arrayList,index + 1, arrayList, index, size - index);
        size--;
    }

    private void addingShift(T value, int index) {
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }
}
