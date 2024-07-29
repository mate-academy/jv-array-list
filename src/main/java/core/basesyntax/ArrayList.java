package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_TO = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        addElement(value);
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexNegativeAndIndexPresent(index);
        grow();
        addElement(value, index);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            addElement(list.get(i));
            size++;
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexNegativeAndIndexPresent(index + 1);
        T result = (T) array[index];
        return result;
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexNegativeAndIndexPresent(index + 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexNegativeAndIndexPresent(index + 1);
        Object[] result = Arrays.copyOf(array, array.length - 1);
        T removedItem = null;
        removedItem = (T) array[index];
        System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        array = result;
        size--;
        return removedItem;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                if (element == null) {
                    remove(getIndex(element));
                    return element;
                }
                continue;
            }
            if (array[i] == element || array[i].equals(element)) {
                remove(getIndex(element));
                return element;
            }
        }

        throw new NoSuchElementException("The element is not present in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        if (array.length < size + 1) {
            Object[] result = new Object[(int) (array.length * GROW_TO) + 1];
            System.arraycopy(array, 0, result, 0, array.length);
            array = result;
        }
    }

    public void addElement(T value) {
        array[size] = value;
    }

    public void addElement(T value, int position) {
        Object[] result = new Object[array.length];
        System.arraycopy(array, 0, result, 0, position);
        result[position] = value;
        System.arraycopy(array, position, result, position + 1, size - position);
        array = result;
    }

    public void checkIfIndexNegativeAndIndexPresent(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }

    public int getIndex(T element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                if (element == null) {
                    index = i;
                }
                continue;
            }
            if (array[i] == element || array[i].equals(element)) {
                index = i;
            }

        }
        return index;
    }
}
