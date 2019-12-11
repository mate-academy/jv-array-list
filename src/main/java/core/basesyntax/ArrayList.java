package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static int CAPACITY;

    private Object[] array;
    private int size = 0;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= array.length) {
            CAPACITY = (array.length * 3) / 2 + 1;
            Object[] copyArray = new Object[CAPACITY];
            for (int i = 0; i < size; i++) {
                copyArray[i] = array[i];
            }
            array = copyArray;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size >= array.length) {
            CAPACITY = (array.length * 3) / 2 + 1;
            Object[] copyArray = new Object[CAPACITY];
            for (int i = 0; i < size; i++) {
                copyArray[i] = array[i];
            }
            array = copyArray;
        } else {
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldValue;
        oldValue = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (t.equals(array[i])) {
                    return remove(i);
                }
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
}
