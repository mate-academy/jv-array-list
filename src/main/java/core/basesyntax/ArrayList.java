package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_ARRAY_SIZE = 10;
    private int size = 0;
    private int index;
    private Object[] array = new Object[START_ARRAY_SIZE];

    @Override
    public void add(T value) {
        if (size >= array.length) {
            array = Arrays.copyOf(array, (int) (array.length * 1.5));
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        } else {
            if (size == array.length) {
                array = Arrays.copyOf(array, (int) (array.length * 1.5));
            }
            Object[] secondPart = Arrays.copyOfRange(array, index, size);
            array[index] = value;
            System.arraycopy(secondPart, 0, array, index + 1, secondPart.length);
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() >= array.length) {
            array = Arrays.copyOf(array, (int) (array.length * 1.5));
        }
        for (int i = size, q = 0; q < list.size(); i++, q++) {
            array[i] = list.get(q);
            size++;
        }
    }

    @Override
    public T get(int index) {
        this.index = index;
        callArrayListIndexOutOfBoundsExceptionIfNeeded();
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        this.index = index;
        callArrayListIndexOutOfBoundsExceptionIfNeeded();
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        this.index = index;
        callArrayListIndexOutOfBoundsExceptionIfNeeded();
        T oldValue = (T) array[index];
        Object[] secondPart = Arrays.copyOfRange(array, index + 1, size);
        System.arraycopy(secondPart, 0, array, index, secondPart.length);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue = null;
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (element == null) {
                if (array[i] == null) {
                    remove(i);
                    counter++;
                    break;
                }
            } else {
                if (array[i] == null) {
                    continue;
                }
                if (array[i].equals(element)) {
                    oldValue = (T) array[i];
                    remove(i);
                    counter++;
                    break;
                }
            }
        }
        if (counter == 0) {
            throw new NoSuchElementException("no such element in List");
        }
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void callArrayListIndexOutOfBoundsExceptionIfNeeded() {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
    }
}
