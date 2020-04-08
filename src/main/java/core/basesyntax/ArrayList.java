package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    public static final int INITIAL_CAPACITY = 10;

    private T[] arrayT;
    private int size;

    public ArrayList() {
        arrayT = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > index || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        grow();
        System.arraycopy(arrayT, index, arrayT, index + 1, size - index);
        arrayT[index] = value;
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
        return arrayT[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayT[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T result = arrayT[index];
            System.arraycopy(arrayT, index + 1, arrayT, index, size - index - 1);
            size--;
            return result;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(T t) {
        return remove(indexOf(t));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        if (size == arrayT.length) {
            int newSize = arrayT.length / 2 * 3;
            arrayT = Arrays.copyOf(arrayT, newSize);
        }
    }

    private int indexOf(T t) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayT[i] == t || arrayT[i] != null && arrayT[i].equals(t)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new java.util.NoSuchElementException();
        }
        return index;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size);
        }
    }

}
