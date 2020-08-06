package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= array.length) {
            ensureCapacity();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds of exception!");
        }
        if (size >= array.length) {
            ensureCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        return array[index];

    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldArray = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size--] = null;
        return oldArray;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size + 1; i++) {
            if (t == array[i] || t != null && t.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element exception: " + t);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        int oldCapacity = array.length;
        int newCapacity = (int) (oldCapacity * 1.5);
        return array = Arrays.copyOf(array, newCapacity);
    }

    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else {
            return true;
        }
    }

    private void ensureCapacity() {
        int newCapacity = (int) (array.length * 1.5);
        T[] oldArray = array;
        array = (T[]) new Object[newCapacity];
        System.arraycopy(oldArray, 0, array, 0, size);
    }
}


