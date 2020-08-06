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
        int count = size;

        if (size == array.length || (array.length - size) < list.size()) {
            array = resize(newCapacity);
        }
        for (int i = 0; i < list.size(); i++) {
            array[count] = list.get(i);
            count += 1;
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        final T result = array[index];

        array[index] = null;
        shrink(index);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        boolean isPresent = false;
        int index = 0;
        T result = null;

        for (int i = 0; i < size; i++) {
            if (array[i] == t || array[i] != null && array[i].equals(t)) {
                index = i;
                result = array[i];
                array[i] = null;
                shrink(index);
                size--;
                isPresent = true;
            }
        }

        if (!isPresent) {
            throw new NoSuchElementException();
        }

        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void shrink(int index) {
        T[] newArray = (T[]) new Object[array.length];
        System.arraycopy(array,0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size - index);
        array = newArray;
    }

    private T[] resize(int newLength) {
        T[] newArray = (T[]) new Object[newLength];

        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }
}
