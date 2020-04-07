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
            ensureCapacity();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (isIndexExist(index)) {
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
        return isIndexExist(index) ? array[index] : null;
    }

    @Override
    public void set(T value, int index) {
        if (isIndexExist(index)) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T temp = null;
        if (isIndexExist(index)) {
            temp = array[index];
            removeElement(index);
        }
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
                removeElement(i);
                return t;
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

    public void removeElement(int index) {
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
    }

    public void ensureCapacity() {
        int newSize = size + (size >> 1);
        T[] oldArray = array;
        array = (T[]) new Object[newSize];
        System.arraycopy(oldArray,0,array,0, size);
    }

    public boolean isIndexExist(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }
}
