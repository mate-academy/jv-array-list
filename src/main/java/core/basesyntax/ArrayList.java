package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_LIST_LENGTH = 10;
    public static final int ONE_ELEMENT = 1;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_LIST_LENGTH];
    }

    @Override
    public void add(T value) {
        growIfNeed(ONE_ELEMENT);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfNeed(ONE_ELEMENT);
        System.arraycopy(elementData, index, elementData, index + ONE_ELEMENT,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfNeed(list.size());
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T[] elementDataCopy = elementData;
        T deletedElement = elementData[index];
        System.arraycopy(elementData, index + ONE_ELEMENT, elementDataCopy, index,
                size - ONE_ELEMENT - index);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        return remove(getElementIndex(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void growIfNeed(int quantity) {
        if (size + quantity > elementData.length) {
            int newCapacity = (elementData.length + (elementData.length >> ONE_ELEMENT));
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("there is no index" + index);
        }
    }

    public void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("there is no index" + index);
        }
    }

    public int getElementIndex(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == element) || (element != null)
                    && element.equals(elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("there is no such element" + element);
    }
}
