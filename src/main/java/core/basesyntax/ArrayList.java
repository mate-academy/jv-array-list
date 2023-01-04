package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final double INCREASING_ARRAY = 1.5;
    private static final int INITIAL_CAPACITY = 10;
    private T[] defaultArray;
    private int size;

    public ArrayList() {
        defaultArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        increaseCapacityIfFull();
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        increaseCapacityIfFull();
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = defaultArray[index];
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(defaultArray[index], element)) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("No such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacityIfFull() {
        if (size == defaultArray.length) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        int newCapacity = (int)(size * INCREASING_ARRAY);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(defaultArray, 0, newArray, 0, defaultArray.length);
        defaultArray = newArray;
    }

//    private void checkIndexForAdd(int index) {
//        if (index > size || index < 0) {
//            throw new ArrayListIndexOutOfBoundsException("Your index is out of Bounds; "
//                    + "index: " + index + " size: " + size);
//        }
//    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of Bounds; "
                    + "index: " + index + " size: " + size);
        }
    }
}
