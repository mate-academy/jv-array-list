package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int INITIAL_SIZE = 0;
    private static final int INITIAL_INDEX = 0;
    private static final String TEXT_OF_EXCEPTION = "Invalid index outside"
            + " the dimension of the array";
    private int size;
    private T[] elementData;

    public ArrayList() {
        size = INITIAL_SIZE;
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            increaseElementDataLength();
        }
        addValueToElementData(value);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < INITIAL_INDEX) {
            throw new ArrayListIndexOutOfBoundsException(TEXT_OF_EXCEPTION);
        } else if (index == elementData.length - 1) {
            increaseElementDataLength();
            addValueToElementData(value);
        } else {
            rightShiftOfElements(value, index);
        }
    }


    @Override
    public void addAll(List<T> list) {
        T[] bufferArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bufferArray[i] = list.get(i);
        }
        if (elementData.length - size < list.size()) {
            increaseElementDataLength();
        }
        System.arraycopy(bufferArray, INITIAL_INDEX, elementData, size, bufferArray.length);
        size += bufferArray.length;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < INITIAL_INDEX) {
            throw new ArrayListIndexOutOfBoundsException(TEXT_OF_EXCEPTION);
        } else {
            return elementData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < INITIAL_INDEX) {
            throw new ArrayListIndexOutOfBoundsException(TEXT_OF_EXCEPTION);
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < INITIAL_INDEX) {
            throw new ArrayListIndexOutOfBoundsException(TEXT_OF_EXCEPTION);
        }
        if (index == size - 1) {
            size--;
            return elementData[index];
        } else {
            T removeValue = elementData[index];
            leftShiftOfElements(index);
            return removeValue;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (null == elementData[i]) {
                    leftShiftOfElements(i);
                    return null;
                }
            } else if (element.equals(elementData[i])) {
                leftShiftOfElements(i);
                return element;
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
        return size == INITIAL_INDEX;
    }

    private void increaseElementDataLength() {
        elementData = Arrays.copyOf(elementData, (int) (elementData.length * 1.5 + 1));
    }

    private void rightShiftOfElements(T value, int index) {
        T[] bufferElementData = (T[]) new Object[elementData.length + 1];
        System.arraycopy(elementData, INITIAL_INDEX, bufferElementData, INITIAL_INDEX, index);
        System.arraycopy(elementData, index, bufferElementData, index + 1, size);
        bufferElementData[index] = value;
        elementData = bufferElementData;
        size++;
    }

    private void addValueToElementData(T value) {
        elementData[size] = value;
        size++;
    }

    private void leftShiftOfElements(int i) {
        T[] bufferElementData = (T[]) new Object[elementData.length - 1];
        System.arraycopy(elementData, INITIAL_INDEX, bufferElementData, INITIAL_INDEX, i);
        System.arraycopy(elementData, i + 1, bufferElementData, i, size - i);
        elementData = bufferElementData;
        size--;
    }
}
