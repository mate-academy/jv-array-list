package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int INDEX_LOWER_BOUND = 0;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private static final String INVALID_INDEX_MESSAGE = "The provided index is invalid. "
            + "Please double-check it. Index: ";
    private static final String INVALID_ELEMENT_MESSAGE = "The provided element is not present "
            + "in the list. Please double-check it.";
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        ensureCapacity();
        System.arraycopy(elementData, index, elementData,
                index + 1, size - index);
        elementData[index] = value;
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
        rangeCheckForRemoveAndSetAndGet(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForRemoveAndSetAndGet(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForRemoveAndSetAndGet(index);
        final T resultValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
        return resultValue;
    }

    @Override
    public T remove(T element) {
        ensureElementsPresence(element);
        int index = getIndex(element);
        T resultValue = get(index);
        remove(index);
        return resultValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        int newCapacity = (int) (elementData.length * CAPACITY_MULTIPLIER);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < INDEX_LOWER_BOUND) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX_MESSAGE + index);
        }
    }

    private void rangeCheckForRemoveAndSetAndGet(int index) {
        if (index >= size || index < INDEX_LOWER_BOUND) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX_MESSAGE + index);
        }
    }

    private void ensureElementsPresence(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(element, i)) {
                return;
            }
        }
        throw new NoSuchElementException(INVALID_ELEMENT_MESSAGE);
    }

    private int getIndex(T element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (isEquals(element, i)) {
                index = i;
            }
        }
        return index;
    }

    private boolean isEquals(T element, int index) {
        return (elementData[index] == element
                || (elementData[index] != null && elementData[index].equals(element)));
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            elementData = grow();
        }
    }
}
