package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DIVIDER_FOR_NEW_CAPACITY = 2;
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final int NON_EXISTING_INDEX = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        addElement(value);
    }

    @Override
    public void add(T value, int index) {
        if (index != 0 && index != size) {
            checkIndex(index);
        }
        if (index == size) {
            add(value);
        } else {
            if (index + size >= elementData.length || size + 1 == elementData.length) {
                elementData = grow();
            }
            for (int i = 0; i < size; i++) {
                if (index == i) {
                    Object[] tempArray = new Object[elementData.length];
                    tempArray[ZERO_INDEX] = elementData[index];
                    elementData[index] = value;
                    index++;
                    size++;
                    tempArray = copyToTempArray(index, tempArray, FIRST_INDEX, size);
                    elementData = copyBackToArray(index, tempArray, size);
                    break;
                }
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size >= elementData.length) {
            elementData = grow();
        }
        for (int i = 0, k = size; i < list.size(); i++, k++) {
            elementData[k] = list.get(i);
            if (i == list.size() - FIRST_INDEX) {
                size = k + FIRST_INDEX;
            }
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        int foundIndex = findIndex(elementData, index);
        if (foundIndex > - 1) {
            elementData[foundIndex] = value;
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = null;
        oldValue = (T) elementData[index];
        if (index == size - FIRST_INDEX) {
            size--;
        } else {
            Object[] tempArray = new Object[size - index];
            index++;
            tempArray = copyToTempArray(index, tempArray, ZERO_INDEX, tempArray.length);
            index--;
            size--;
            elementData = copyBackToArray(index, tempArray, tempArray.length);
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                remove(i);
                break;
            } else if (i == size - FIRST_INDEX) {
                throw new NoSuchElementException("Element " + element + " wasn't found");
            }
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == ZERO_INDEX;
    }

    private void checkIndex(int index) {
        if (index >= size || index < ZERO_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }

    private int findIndex(Object[] tempArr, int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return i;
            }
        }
        return NON_EXISTING_INDEX;
    }

    private Object[] copyBackToArray(int index, Object[] tempArray, int size) {
        for (int j = 0, k = index; j < size; j++, k++) {
            elementData[k] = tempArray[j];
        }
        return elementData;
    }

    private Object[] copyToTempArray(int index, Object[] tempArray, int startIndex, int size) {
        for (int j = startIndex, k = index; j < size; j++, k++) {
            tempArray[j] = elementData[k];
        }
        return tempArray;
    }

    private void addElement(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    private Object[] grow() {
        return grow(size + FIRST_INDEX);
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        return oldCapacity + (oldCapacity / DIVIDER_FOR_NEW_CAPACITY);
    }
}
