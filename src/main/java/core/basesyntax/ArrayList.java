package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int CAPACITY = 16;
    private static final int RATE = 4;
    private Object[] array = new Object[CAPACITY];
    private int size = 0;

    public ArrayList() {

        T elementData = (T) new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length - 1) {
            resize(array.length * 2);
        }
        array[size++] = value;

    }

    @Override
    public void add(T value, int index) {
        if (index < size()) {
            resize(size() * 2);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removeItem = (T) array[index];
        for (int i = index; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removeItem;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t != null && t.equals(array[i]) || t == null && array[i] == null) {
                remove(i);
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

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
