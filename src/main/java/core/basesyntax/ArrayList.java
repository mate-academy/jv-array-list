package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 12;
    private T[] currentArray;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            currentArray = (T[]) new Object[initialCapacity > DEFAULT_CAPACITY
                    ? initialCapacity : DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    public ArrayList() {
        currentArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T[] grow() {
        return currentArray = Arrays.copyOf(currentArray,
                (int) (currentArray.length * 1.5));
    }

    @Override
    public void add(T value) {
        if (currentArray.length - 1 <= size) {
            currentArray = grow();
        }
        currentArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (currentArray.length - 1 <= size) {
                currentArray = grow();
            }
            for (int i = size; i > index; i--) {
                currentArray[i] = currentArray[i - 1];
            }
            size++;
            currentArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't add element with index " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (currentArray.length < size + list.size()) {
            currentArray = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            currentArray[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return currentArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't get element with index " + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            currentArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't set element with index " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T removedObject = currentArray[index];
            for (int i = index; i < size; i++) {
                currentArray[i] = currentArray[i + 1];
            }
            currentArray[size - 1] = null;
            size--;
            return removedObject;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element with index " + index);
        }
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (currentArray[i] == element
                    || (element != null && element.equals(currentArray[i]))) {
                index = i;
                size--;
                break;
            }
        }
        if (index < 0) {
            throw new NoSuchElementException("Can't remove element " + element);
        }
        for (int i = index; i < size; i++) {
            currentArray[i] = currentArray[i + 1];
        }
        return element;
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
