package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_NUMBER = 1.5;
    private static final String INDEX_OUT_OF_BOUNDS = "This index is not correct: ";
    private static final String ELEMENT_EXCEPTION = "Element not found: ";
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
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
        checkIndex(index, false);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T removeIndex = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removeIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == array[i]) || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(ELEMENT_EXCEPTION + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, boolean isAdd) {
        if (isAdd && (index < 0 || index > size)) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS + index);
        }
        if (!isAdd && (index < 0 || index >= size)) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS + index);
        }
    }

    private void growIfArrayFull() {
        if (size >= array.length) {
            int newCapacity = (int) (array.length * GROWTH_NUMBER);
            T[] newArray = (T[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }
}
