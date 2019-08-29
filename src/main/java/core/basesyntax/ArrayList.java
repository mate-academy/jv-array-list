package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private T[] array;
    private int sizeArr;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        sizeArr = 0;
    }

    private void increaseCapacity() {
        if (sizeArr >= array.length) {
            array = Arrays.copyOf(array, array.length + DEFAULT_CAPACITY);
        }
    }

    private void checkIndexException(int index) {
        if (index < 0 || index >= sizeArr) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void add(T value) {
        increaseCapacity();
        array[sizeArr] = value;
        sizeArr++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexException(index);
        for (int i = sizeArr; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        sizeArr++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexException(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexException(index);
        T removeValue = array[index];
        for (int i = index; i < sizeArr; i++) {
            array[i] = array[i + 1];
        }
        sizeArr--;
        return removeValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < sizeArr; i++) {
            if (array[i] == t) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return sizeArr;
    }

    @Override
    public boolean isEmpty() {
        return sizeArr == 0;
    }
}
