package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int GROWTH_FACTOR = 2;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private int currentSize = DEFAULT_SIZE;
    private Object[] elements = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        growArrayIfFull();
        elements[currentSize] = value;
        currentSize += 1;
    }

    @Override
    public void add(T value, int index) {
        checkInRangeForAddition(index);
        growArrayIfFull();
        System.arraycopy(elements, index, elements, index + 1, currentSize - index);
        elements[index] = value;
        currentSize += 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkInRangeForGet(index);
        return (T) (elements[index]);
    }

    @Override
    public void set(T value, int index) {
        checkInRangeForGet(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkInRangeForGet(index);
        Object temp = elements[index];

        if (index != currentSize - 1) {
            System.arraycopy(elements, index + 1, elements, index, currentSize - index);
        }
        currentSize -= 1;
        return (T) (temp);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(elements[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void growArrayIfFull() {
        if (size() == elements.length) {
            Object[] temp = new Object[elements.length + elements.length / GROWTH_FACTOR + 1];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
    }

    private void checkInRangeForGet(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", for Size: " + currentSize);
        }
    }

    private void checkInRangeForAddition(int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", for Size: " + currentSize + 1);
        }
    }
}
