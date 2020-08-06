package core.basesyntax;

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

    @Override
    public void add(T value) {
        int newLength = (int) (size * 1.5);

        if (size == array.length) {
            array = resize(newLength);
        }

        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        T[] newArray = (T[]) new Object[array.length];

        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (size == array.length) {
            newArray = resize((int) (newArray.length * 1.5));
        }
        if (index == 0) {
            System.arraycopy(array, 0, newArray, 1, array.length - 1);
            newArray[0] = value;
            array = newArray;
            size++;
        } else {
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = value;
            System.arraycopy(array, index, newArray, index + 1, size - index);
            array = newArray;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newCapacity = size + list.size();

        if (newCapacity >= array.length) {
            array = resize(newCapacity);
        }
        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        validateInput(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        validateInput(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateInput(index);

        final T result = array[index];
        T[] newArray = (T[]) new Object[array.length];

        array[index] = null;
        System.arraycopy(array,0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size - index);
        array = newArray;
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        int index = 0;

        for (int i = 0; i < size; i++) {
            if (array[i] == t || array[i] != null && array[i].equals(t)) {
                index = i;
                remove(index);
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

    private T[] resize(int newLength) {
        T[] newArray = (T[]) new Object[newLength];

        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    private void validateInput(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
}
