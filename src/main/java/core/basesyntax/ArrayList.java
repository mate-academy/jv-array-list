package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final double RESIZE_FACTOR = 1.5;

    private int size;
    private int capacity;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = DEFAULT_SIZE;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkBounds(index, true);

        if (index == size) {
            add(value);
            return;
        }

        growIfArrayFull();
        int elementsToShift = ++size - index - 1;
        System.arraycopy(array, index, array, index + 1, elementsToShift);
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        ensureCapacity(listSize);

        for (int i = 0; i < listSize; i++) {
            add(list.get(i));
        }
    }

    private void ensureCapacity(int listLength) {
        while (!(capacity > size + listLength)) {
            grow();
        }
    }

    private void growIfArrayFull() {
        if (size == capacity) {
            grow();
        }
    }

    private void grow() {
        capacity = (int) (capacity * RESIZE_FACTOR);
        array = Arrays.copyOf(array, capacity);
    }

    @Override
    public T get(int index) {
        checkBounds(index, false);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index, false);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index, false);
        T objectRemoved = (T) array[index];
        int elementsToShift = size - index - 1;
        System.arraycopy(array, index + 1, array, index, elementsToShift);
        array[--size] = null;
        return objectRemoved;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T targetElement = array[i];

            if ((targetElement == null && element == null)
                    || (targetElement != null && targetElement.equals(element))) {
                remove(i);
                return targetElement;
            }
        }

        throw new NoSuchElementException("Element " + element.toString() + " does not exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /* There is conflict during checking bounds when getting or adding
       a value right after last index, hence the boolean in parameter.
       When checking with 'true', it won't throw exception if index=size. */
    private void checkBounds(int index, boolean allowEqualSize) {
        if (index < 0 || (index > size - 1 && (!allowEqualSize || index != size))) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds.");
        }
    }
}
