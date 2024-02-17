package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASING_SIZE = 1.5;
    private static final int START_POSITION = 0;
    private static final int STEP_SIZE = 1;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %d out of bounds for size: %d";
    private int size;
    private Object[] array = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size] = value;
            increaseSize();
        } else {
            increaseArray();
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (size < array.length && index <= size && index >= 0) {
            shiftArrayWithAddingElement(value, index);
        } else if (size == array.length) {
            increaseArray();
            shiftArrayWithAddingElement(value, index);
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
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
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        size--;
        T removedElement = (T) array[index];
        shiftArray(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int indexOfRemovedElement = 0;
        T removedElement = null;
        boolean isFound = false;
        for (Object o : array) {
            if (element == o || (element != null && element.equals(o))) {
                removedElement = remove(indexOfRemovedElement);
                isFound = true;
                break;
            }
            indexOfRemovedElement++;
        }
        if (!isFound) {
            throw new NoSuchElementException("No such element " + element + " in the list");
        }
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    private void increaseArray() {
        Object[] increasedArray = new Object[(int) (array.length * INCREASING_SIZE)];
        copyArrayFromStartPosition(array.length, increasedArray);
        array = increasedArray;
    }

    private void shiftArrayWithAddingElement(T value, int index) {
        Object[] shiftedArray = new Object[array.length];
        copyArrayFromStartPosition(index, shiftedArray);
        shiftedArray[index] = value;
        System.arraycopy(array, index, shiftedArray,
                index + STEP_SIZE, (array.length - STEP_SIZE) - index);
        array = shiftedArray;
        increaseSize();
    }

    private void shiftArray(int index) {
        if (index == size) {
            array[index] = null;
        } else {
            Object[] shiftedArray = new Object[array.length];
            copyArrayFromStartPosition(index, shiftedArray);
            System.arraycopy(array, index + STEP_SIZE, shiftedArray,
                    index, (array.length - STEP_SIZE) - index);
            array = shiftedArray;
        }
    }

    private void copyArrayFromStartPosition(int index, Object[] shiftedArray) {
        System.arraycopy(array, START_POSITION, shiftedArray,
                START_POSITION, index);
    }

    private void increaseSize() {
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
        }
    }
}
