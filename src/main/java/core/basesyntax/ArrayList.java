package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String EXEPTION_MASAGE = "Invalid index";
    private T[] defaultArray;
    private int size;

    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        capacityCheck();
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        capacityCheck();
        if (isIndexValid(index) || index == size) {
            for (int i = size; i > index; i--) {
                defaultArray[i] = defaultArray[i - 1];
            }
            defaultArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException(EXEPTION_MASAGE);
        }
    }

    @Override
    public void addAll(List<T> list) {
        size += list.size();
        int indexList = 0;
        capacityCheck();
        for (int i = size - list.size(); i < size; i++) {
            defaultArray[i] = list.get(indexList);
            indexList++;
        }
    }

    @Override
    public T get(int index) {
        if (isIndexValid(index)) {
            return defaultArray[index];
        }
        throw new ArrayListIndexOutOfBoundsException(EXEPTION_MASAGE);
    }

    @Override
    public void set(T value, int index) {
        if (isIndexValid(index)) {
            defaultArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(EXEPTION_MASAGE);
        }
    }

    @Override
    public T remove(int index) {
        T removedElement;
        if (isIndexValid(index)) {
            removedElement = defaultArray[index];
            if (size - 1 - index >= 0) {
                System.arraycopy(defaultArray, index + 1, defaultArray, index, size - 1 - index);
            }
            size--;
            return removedElement;
        }
        throw new ArrayListIndexOutOfBoundsException(EXEPTION_MASAGE);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (defaultArray[i] == element
                    || (defaultArray[i] != null
                    && defaultArray[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(EXEPTION_MASAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void capacityCheck() {
        if (size >= defaultArray.length) {
            T[] tempArray = (T[]) new Object[(int) (defaultArray.length * 1.5)];
            System.arraycopy(defaultArray, 0, tempArray, 0, defaultArray.length);
            defaultArray = tempArray;
        }
    }

    private boolean isIndexValid(int index) {
        return index < size & index >= 0;
    }
}
