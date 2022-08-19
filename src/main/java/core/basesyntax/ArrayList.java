package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeArrayIfFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resizeArrayIfFull();
        if (elements[index] == null) {
            elements[index] = value;
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        resizeArrayIfFull();
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkElementExistsByIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementExistsByIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementExistsByIndex(index);
        T removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = findByValue(element);
        checkElementExistsByIndex(index);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArrayIfFull() {
        if (elements.length == size) {
            Object[] resizedArray = new Object[(elements.length * 2)];
            System.arraycopy(this.elements, 0, resizedArray, 0, size);
            elements = (T[]) resizedArray;
        }

    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect input index: " + index);
        }
    }

    private void checkElementExistsByIndex(int index) {
        if (index < 0 || (index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect input index: " + index);
        }
    }

    private int findByValue(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == null
                    ? elements[i] == element : elements[i].equals(element)) {
                index = i;
            }
        }
        if (index < 0) {
            throw new NoSuchElementException("Element " + element + " not found");
        }
        return index;
    }
}
