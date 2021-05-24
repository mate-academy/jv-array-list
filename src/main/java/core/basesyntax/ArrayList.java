package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array = (T[]) new Object[DEFAULT_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!(isValid(index) || (index == size))) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index");
        }
        System.arraycopy(array, index, array, index + 1, size - index);
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
        if (isValid(index)) {
            return array[index];
        }
        throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index!");
    }

    @Override
    public void set(T value, int index) {
        if (isValid(index)) {
            array[index] = value;
        } else {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index!");
        }

    }

    @Override
    public T remove(int index) {
        if (isValid(index)) {
            T result = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return result;
        }
        throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Wrong index!");
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(array[index], element)) {
                return remove(index);
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

    private boolean isValid(int index) {
        return index >= 0 && index < size;
    }
}
