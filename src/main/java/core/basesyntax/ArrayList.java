package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int size = 0;
    private T[] array = (T[]) new Object[10];

    @Override
    public void add(T value) {
        if (size + 1 > array.length - 1) {
            array = grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else if (index < size && index >= 0) {
            if (size + 1 > array.length - 1) {
                array = grow();
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return array[index];
        } else {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            array[index] = value;
        } else {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index!");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T result = array[index];
            System.arraycopy(array, index + 1, array, index, size - index);
            size--;
            return result;
        } else {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index!");
        }
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(array[index], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElement");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        return Arrays.copyOf(array,array.length
                + (array.length >> 2));
    }
}
