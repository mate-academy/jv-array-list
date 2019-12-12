package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int BEGINNER_CAPACITY = 10;
    private Object[] array;
    private int realCapacity;
    private int size;

    public ArrayList() {
        array = new Object[BEGINNER_CAPACITY];
        realCapacity = BEGINNER_CAPACITY;
    }

    @Override
    public void add(T value) {
        checkSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        for (int i = size(); i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkSize();
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Object value = array[index];
        for (int i = index; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size()] = null;
        size--;
        return (T) value;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size(); i++) {
            if (t != null ? t.equals(array[i]) : t == array[i]) {
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
        int count = 0;
        for (int i = 0; i < size(); i++) {
            count++;
        }
        return count == 0;
    }

    private void checkSize() {
        if (size() == array.length) {
            realCapacity = realCapacity * 3 / 2;
        }
        array = Arrays.copyOf(array, realCapacity);
    }

}
