package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double INCREMENT_STEP = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        isElementDataFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of Bounds Exception: " + index);
        }
        isElementDataFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        isIndexInRange(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInRange(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexInRange(index);
        T oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such an element is missing in ArrayList: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isElementDataFull() {
        if (size == elementData.length) {
            increaseSize();
            return true;
        }
        return false;
    }

    private boolean isIndexInRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds: " + index);
        }
        return true;
    }

    private boolean isEqual(T firstElement, T secondElement) {
        return (firstElement == secondElement || firstElement != null
                && firstElement.equals(secondElement));
    }

    private void increaseSize() {
        int newCapacity = (int) (size * INCREMENT_STEP);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
}
