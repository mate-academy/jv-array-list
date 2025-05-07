package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_INCREASE = 1;
    private static final int RIGHT_SHIFT = 1;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int INDEX_IS_NOT_PRESENT = -1;
    private static final String NO_SUCH_ELEMENT_MESSAGE =
            "There is no such element [%h] in the list";
    private static final String OUT_OF_BOUNDS_MESSAGE = "Array size is %d. Index %d out of bounds.";
    private Object[] arrayList;
    private int currentSize;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    private T removeElementByIndex(int index) {
        T element = get(index);
        System.arraycopy(arrayList, index + 1, arrayList, index, currentSize - index - 1);
        currentSize--;
        return element;
    }

    @Override
    public void add(T value) {
        checkSize();
        arrayList[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        checkSize();
        System.arraycopy(arrayList, index, arrayList, index + 1, currentSize - index);
        arrayList[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index + 1);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        return removeElementByIndex(index);
    }

    @Override
    public T remove(T element) {
        int index = indexOfElement(element);
        if (index == INDEX_IS_NOT_PRESENT) {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_MESSAGE, element));
        }
        return removeElementByIndex(index);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private Object[] grow() {
        return Arrays.copyOf(arrayList, currentSize + (currentSize >> RIGHT_SHIFT));
    }

    private void checkSize() {
        if (currentSize + SIZE_INCREASE > arrayList.length) {
            arrayList = grow();
        }
    }

    private void checkIndex(int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(OUT_OF_BOUNDS_MESSAGE, currentSize, index));
        }
    }

    private int indexOfElement(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (arrayList[i] == element
                    || arrayList[i] != null && arrayList[i].equals(element)) {
                return i;
            }
        }
        return INDEX_IS_NOT_PRESENT;
    }
}
