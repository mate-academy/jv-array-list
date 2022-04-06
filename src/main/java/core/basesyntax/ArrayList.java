package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_LENGTH = 10;

    private T[] array;
    private int size = 0;

    ArrayList() {
        array = (T[]) new Object[ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size != array.length) {
            array[size] = value;
            size++;
        } else {
            resize();
            array[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        if (size == array.length) {
            resize();
        }
        if (index == size) {
            array[index] = value;
            size++;
        } else if (index < size) {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int arraySize = size;
        for (int i = arraySize; i < arraySize + list.size(); i++) {
            if (size == array.length) {
                resize();
            }
            array[i] = list.get(i - arraySize);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        T temp = array[index];
        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        if (size != 0) {
            for (int i = 0; i < size - 1; i++) {
                if (array[i] == null) {
                    if (array[i] == element) {
                        return remove(i);
                    }
                    continue;
                }
                if (array[i].equals(element)) {
                    return remove(i);
                }
            }
            throw new NoSuchElementException("Element isn't exist");
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    private T[] resize() {
        int newLength = array.length + (array.length >> 1);
        return array = Arrays.copyOf(array, newLength);
    }
}
