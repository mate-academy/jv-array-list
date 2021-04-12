package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String NO_SUCH_ELEMENT = "No such element in array!";
    private static final String INVALID_INDEX = "Invalid index!";
    private static final String INDEX_NOT_EXIST = "Index doesn't exist!";
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        changeArrayCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        changeArrayCapacity();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_NOT_EXIST);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedIndex = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return deletedIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX);
        }
    }

    private void changeArrayCapacity() {
        if (elements.length == size) {
            T[] resizedArray = (T[]) new Object[size + (size / 2)];
            System.arraycopy(elements, 0, resizedArray, 0, size);
            elements = resizedArray;
        }
    }
}
