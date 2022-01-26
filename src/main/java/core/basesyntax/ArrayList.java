package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public ArrayList() {
        elements = new Object[]{};
        size = 0;
    }

    private Object[] grow() {
        if (size == 0) {
            return new Object[DEFAULT_CAPACITY];
        }
        return Arrays.copyOf(elements, elements.length * 3 / 2);
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size] = value;
        size ++;
    }

    private boolean isValidIndexCheck(int index) {
        return index <= size && index >= 0;
    }

    private String indexOutOfBoundMassage(int index) {
        return "index: " + index + " size: " + size;
    }

    private void indexCheck(int index) {
        if (!isValidIndexCheck(index)) {
            throw new ArrayListIndexOutOfBoundsException(indexOutOfBoundMassage(index));
        }
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index);
        if (index == size) {
            add(value);
            return;
        }

        if (size == elements.length) {
            elements = grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size ++;
    }

    @Override
    public void addAll(List<T> list) {
        for(int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private boolean isValidIndexPositionCheck(int index) {
        return index < size && index >= 0;
    }

    private String indexPositionOutOfBoundMassage(int index) {
        return "index: " + index + " length: " + elements.length;
    }


    private void indexPositionCheck(int index) {
        if (!isValidIndexPositionCheck(index)) {
            throw new ArrayListIndexOutOfBoundsException(indexPositionOutOfBoundMassage(index));
        }
    }

    @Override
    public T get(int index) {
        indexPositionCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexPositionCheck(index);
        elements[index] = value;
    }

    private void deleteElement(int index) {
        indexPositionCheck(index);
        final int newSize = size - 1;
        if (newSize > 0) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        elements[size = newSize] = null;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        deleteElement(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(get(i),element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
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
