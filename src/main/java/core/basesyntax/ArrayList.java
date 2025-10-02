package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE = 10;
    public static final double GROWTH_FACTOR = 1.5;
    public static final String WRONG_INDEX_MESSAGE_P1 = "Index : ";
    public static final String WRONG_INDEX_MESSAGE_P2 = ", Size: ";
    public static final String NO_ELEMENT_MESSAGE = "Element not found: ";
    private int capacity;
    private T[] classList;
    private int nextFreeIndex;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.classList = (T[]) new Object[DEFAULT_SIZE];
        this.nextFreeIndex = 0;
        this.capacity = DEFAULT_SIZE;
    }

    @Override
    public void add(T value) {
        if (nextFreeIndex == capacity) {
            grow();
        }
        classList[nextFreeIndex] = value;
        nextFreeIndex++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(WRONG_INDEX_MESSAGE_P1 + index
                    + WRONG_INDEX_MESSAGE_P2 + size());
        }
        if (nextFreeIndex == capacity) {
            grow();
        }
        T[] newArr = (T[]) new Object[capacity];
        if (index > 0) {
            System.arraycopy(classList, 0, newArr, 0, index);
        }
        if (index <= size()) {
            System.arraycopy(classList, index, newArr, index + 1, size() - index);
        }
        newArr[index] = value;
        classList = newArr;
        nextFreeIndex++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIfIndexExist(index);
        return classList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexExist(index);
        classList[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIfIndexExist(index);
        T[] newArr = (T[]) new Object[capacity];
        if (index > 0) {
            System.arraycopy(classList, 0, newArr, 0, index);
        }
        if (index < size()) {
            System.arraycopy(classList, index + 1, newArr, index, size() - index - 1);
        }
        T removedObject = get(index);
        classList = newArr;
        nextFreeIndex--;
        return removedObject;

    }

    @Override
    public T remove(T element) {
        boolean foundElement = false;
        for (int i = 0; i < size(); i++) {
            if (element == null && classList[i] == null) {
                remove(i);
                foundElement = true;
                break;
            }
            if (classList[i] != null && classList[i].equals(element)) {
                remove(i);
                foundElement = true;
                break;
            }
        }
        if (!foundElement) {
            throw new NoSuchElementException(NO_ELEMENT_MESSAGE + element);
        }
        return element;
    }

    @Override
    public int size() {
        return nextFreeIndex;
    }

    @Override
    public boolean isEmpty() {
        return nextFreeIndex == 0;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int oldSize = capacity;
        int newSize = (int) (oldSize * GROWTH_FACTOR);
        T[] newArr = (T[]) new Object[newSize];
        System.arraycopy(classList, 0, newArr, 0, oldSize);
        capacity = newSize;
        classList = newArr;
    }

    private void checkIfIndexExist(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(WRONG_INDEX_MESSAGE_P1 + index
                    + WRONG_INDEX_MESSAGE_P2 + size());
        }
    }
}
