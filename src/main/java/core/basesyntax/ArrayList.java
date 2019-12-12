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
        System.arraycopy(array, index, array,index + 1, size - index);
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
        final Object value = array[index];
        System.arraycopy(array,index + 1, array, index, size - index - 1);
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
        return size() == 0;
    }

    private void checkSize() {
        if (size() == array.length) {
            realCapacity = realCapacity * 3 / 2;
        }
        array = Arrays.copyOf(array, realCapacity);
    }
}
