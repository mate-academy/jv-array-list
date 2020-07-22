package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int CAPACITY = 10;

    private T[] arrayList;
    private int size = 0;

    public ArrayList() {
        arrayList = (T[]) new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            increaseArrayList();
        }
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == arrayList.length) {
            increaseArrayList();
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
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T element = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[--size] = null;
        return element;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null && arrayList[i] == null
                    || t != null && t.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArrayList() {
        int lengthArrayList = arrayList.length;
        lengthArrayList = lengthArrayList + (lengthArrayList >> 1);
        T[] newArrayList = (T[]) new Object[lengthArrayList];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        arrayList = newArrayList;
    }
}
