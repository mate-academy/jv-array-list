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
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void newCapacity() {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length * 3 / 2);
        }
    }

    private void checkForArrayIndexOutOfBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void add(T value) {
        newCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkForArrayIndexOutOfBoundsException(index);
        if (size >= 0) {
            System.arraycopy(array, 0, array, 1, size);
        }
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
        checkForArrayIndexOutOfBoundsException(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkForArrayIndexOutOfBoundsException(index);
        array[index] = value;

    }

    @Override
    public T remove(int index) {
        checkForArrayIndexOutOfBoundsException(index);
        T removeValue = array[index];
        if (size - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        size--;
        return removeValue;
    }

    @Override
    public T remove(T t) {

        for (int i = 0; i < size; i++) {
            if (array[i] == null || array[i].equals(t)) {
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
        return array[0] == null;

    }
}
