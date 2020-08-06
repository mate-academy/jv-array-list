package core.basesyntax;

import java.util.NoSuchElementException;

/*
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private final int CAPACITY = 10;
    private T[] arrayGeneric;
    private int size;

    public ArrayList() {
        arrayGeneric = (T[]) new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayGeneric.length) {
            arrayGeneric = resize();
        }
        arrayGeneric[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This index is not suitable for the given array");
        }

        if (size == arrayGeneric.length) {
            arrayGeneric = resize();
        }
        System.arraycopy(arrayGeneric, index, arrayGeneric, index + 1, size - index);
        arrayGeneric[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= arrayGeneric.length) {
            arrayGeneric = resize();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arrayGeneric[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayGeneric[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return editArray(index);
    }

    @Override
    public T remove(T t) {
        int index = findRemoveElement(t);
        if (index != -1) {
            return editArray(index);
        }
        throw new NoSuchElementException("No matching item found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] resize() {
        T[] currentArray = (T[]) new Object[(arrayGeneric.length * 3) / 2 + 1];
        System.arraycopy(arrayGeneric, 0, currentArray, 0, size);
        return currentArray;
    }

    public int findRemoveElement(T element) {
        for (int i = 0; i < arrayGeneric.length; i++) {
            if (arrayGeneric[i] == element || arrayGeneric[i] != null
                    && arrayGeneric[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public T editArray(int index) {
        T element = arrayGeneric[index];
        System.arraycopy(arrayGeneric, index + 1, arrayGeneric, index, size - (index + 1));
        arrayGeneric[--size] = null;
        return element;
    }

    public boolean checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This index is not suitable for the given array");
        }
        return true;
    }

}
