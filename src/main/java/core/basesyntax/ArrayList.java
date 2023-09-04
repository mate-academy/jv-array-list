package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeArrayIfNeeded();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (isInvalidIndexAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        } else {
            Object[] arrayPart2 = Arrays.copyOfRange(array, index, size);
            resizeArrayIfNeeded();
            System.arraycopy(arrayPart2, 0, array, index + 1, size - index);
            array[index] = value;
            size++;
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
        if (isInvalidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        } else {
            return (T) array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (isInvalidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (isInvalidIndex(index)) {
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No Such Element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArrayIfNeeded() {
        if (size >= array.length) {
            array = Arrays.copyOf(array, (int) (array.length * GROWTH_FACTOR));
        }
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= size;
    }

    private boolean isInvalidIndexAdd(int index) {
        return index < 0 || index > size;
    }
}
