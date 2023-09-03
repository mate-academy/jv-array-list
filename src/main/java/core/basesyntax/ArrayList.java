package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] array = new Object[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (array.length * GROWTH_FACTOR));
        }
        array[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (array.length * GROWTH_FACTOR));
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int quantity = size + list.size();
        int arrayLength = array.length;
        if (quantity > arrayLength) {
            while (quantity >= arrayLength) {
                arrayLength = (int) (arrayLength * GROWTH_FACTOR);
            }
            array = Arrays.copyOf(array, arrayLength);
        }
        int index = size;
        for (int i = 0; i < list.size(); i++) {
            array[index] = list.get(i);
            index++;
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        } else {
            T removedElement = (T) array[index];
            Object[] arrayPart2 = Arrays.copyOfRange(array, index + 1, array.length);
            System.arraycopy(arrayPart2, 0, array, index, arrayPart2.length);
            size--;
            return removedElement;
        }
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            boolean isElementEqual = element == null || array[i] == null
                    ? element == array[i] : array[i].equals(element);
            if (isElementEqual) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("No Such Element");
        } else {
            return remove(index);
        }
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
