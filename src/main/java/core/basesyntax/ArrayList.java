package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= array.length) {
            growCapacity();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex(index)) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return checkIndex(index) ? array[index] : null;
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (checkIndex(index)) {
            value = array[index];
            removeElement(index);
        }
        return value;
    }

    @Override
    public T remove(T el) {
        for (int i = 0; i < size; i++) {
            if (array[i] == el
                    || (array[i] != null && array[i].equals(el))) {
                removeElement(i);
                return el;
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

    private void growCapacity() {
        int newCapacity = (size * 3) / 2 + 1;
        T[] oldArray = array;
        array = (T[]) new Object[newCapacity];
        System.arraycopy(oldArray, 0, array, 0, size);

    }

    private boolean checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

    private void removeElement(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }
}
