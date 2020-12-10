package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            array = grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            if (size == array.length) {
                array = grow();
            }
            for (int i = size - 1; i >= index; i--) {
                moveRight(i);
            }
            array[index] = value;
            size++;
        }

    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return array[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            array[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        T temp;
        if (index < size && index >= 0) {
            temp = array[index];
            for (int i = index + 1; i < size; i++) {
                moveLeft(i);
            }
            array[size - 1] = null;
            size--;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
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

    private void moveRight(int index) {
        array[index + 1] = array[index];
    }

    private void moveLeft(int index) {
        array[index - 1] = array[index];
    }

    private T[] grow() {
        return array = Arrays.copyOf(array, (int) (array.length * 1.5));
    }
}
