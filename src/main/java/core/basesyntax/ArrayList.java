package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_NUMBER = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void growIfArrayFull() {
        if (size >= array.length) {
            int newCapacity = (int) (array.length * GROWTH_NUMBER);
            if (newCapacity <= size) {
                newCapacity = size + 1;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Such index can not be add" + index);
        }
        growIfArrayFull();
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
        if (index < 0 | index > size) {
            throw new ArrayListIndexOutOfBoundsException("Such index don not exist");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can not set in such index");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 | index > size) {
            throw new ArrayListIndexOutOfBoundsException("Such index don not exist");
        }
        T removeIndex = get(index);
        int moveStep = size - index - 1;
        if (moveStep > 0) {
            System.arraycopy(array, index + 1, array, index, moveStep);
        }
        array[--size] = null;
        return removeIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || (element != null && element.equals(array[i]))) {
                T removedElement = array[i];
                int moveStep = size - i - 1;
                if (moveStep > 0) {
                    System.arraycopy(array, i + 1, array, i, moveStep);
                }
                array[--size] = null;
                return removedElement;
            }
        }
        return null;
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
