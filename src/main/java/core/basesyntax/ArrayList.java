package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (array.length > size) {
            addElement(value);
        } else {
            grow();
            addElement(value);
        }
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexNegative(index);
        checkIfIndexPresent(index);
        if (array.length > size) {
            addElement(value, index);
        } else {
            grow();
            addElement(value, index);
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        if (newSize < array.length) {
            copyArray(list);
        } else {
            checkIfArrayShouldGrow(list);
            copyArray(list);
        }

        size += list.size();
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexNegative(index);
        checkIfPositionExistent(index);
        T result = (T) array[index];
        return result;
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexNegative(index);
        checkIfPositionExistent(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexNegative(index);
        checkIfPositionExistent(index);
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
        boolean flag = false;

        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                if (element == null) {
                    remove(getIndex(element));
                    flag = true;
                    return element;
                }
                continue;
            }
            if (array[i] == element || array[i].equals(element)) {
                remove(getIndex(element));
                flag = true;
                return element;
            }

        }
        if (flag == false) {
            throw new NoSuchElementException("The element is not present in the list");
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

    public void grow() {
        Object[] result = new Object[(int) (array.length * 1.5) + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        array = result;
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

    public void copyArray(List<T> list) {
        Object[] result = Arrays.copyOf(array, array.length);
        System.arraycopy(makeListArray(list), 0, result, size, list.size());
        array = result;
    }

    public void checkIfArrayShouldGrow(List<T> list) {
        int newSize = size + list.size();
        while (array.length < newSize) {
            grow();
        }
    }

    public Object[] makeListArray(List<T> list) {
        Object[] result = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public void checkIfIndexNegative(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is negative");
        }
    }

    public void checkIfPositionExistent(int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }

    public void checkIfIndexPresent(int index) {
        if (index > size) {
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
