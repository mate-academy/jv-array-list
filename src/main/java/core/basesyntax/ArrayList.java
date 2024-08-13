package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final float RESIZE_THRESHOLD = 1.0f;
    private static final float RESIZE_MULTIPLIER = 1.5f;
    private static final int INITIAL_POSITION = 0;
    private static final int INDEX_SHIFT = 1;
    private static final String ARRAY_INDEX_EXCEPTION_FORMAT =
            "Index = [%d] is out of range for ArrayList with size = [%d]";
    private static final String ELEMENT_NOT_FOUND_FORMAT =
            "Element = [%s] can't be found in the ArrayList";
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        resizeIfRequired();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateInsertionIndex(index);
        resizeIfRequired();
        System.arraycopy(array, index, array, index + INDEX_SHIFT, size - index);
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
        validateIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T value = (T) array[index];
        System.arraycopy(array, index + INDEX_SHIFT, array, index, size - index - INDEX_SHIFT);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && array[i] == null) {
                continue;
            }
            if (element == array[i] || array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(String.format(ELEMENT_NOT_FOUND_FORMAT, element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throwArrayListIndexOutOfBounds(index);
        }
    }

    private void validateInsertionIndex(int index) {
        if (index > size || index < 0) {
            throwArrayListIndexOutOfBounds(index);
        }
    }

    private void throwArrayListIndexOutOfBounds(int index) {
        String message = String.format(ARRAY_INDEX_EXCEPTION_FORMAT, index, size);
        throw new ArrayListIndexOutOfBoundsException(message);
    }

    private void resizeIfRequired() {
        if (size / (float) array.length >= RESIZE_THRESHOLD) {
            Object[] newArray = new Object[(int) (size * RESIZE_MULTIPLIER)];
            System.arraycopy(array, INITIAL_POSITION, newArray, INITIAL_POSITION, size);
            array = newArray;
        }
    }
}
