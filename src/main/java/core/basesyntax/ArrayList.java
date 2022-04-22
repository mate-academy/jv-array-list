package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY_OF_ELEMENT_DATA = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[CAPACITY_OF_ELEMENT_DATA];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            elementData = increaseSizeArray(elementData);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size >= elementData.length) {
            elementData = increaseSizeArray(elementData);
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Yuo can't add "
                    + "element by this index");
        }
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("You can't use this "
                    + "index");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("You can't set value "
                    + "to this position");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T temp = null;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element by this "
                    + "index doesn't exist");
        } else {
            temp = elementData[index];
            elementData[index] = null;
            moveElementsAfterRemove(index);
            size--;
        }
        return temp;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < elementData.length; i++) {
                if (elementData[i] == null) {
                    moveElementsAfterRemove(i);
                    size--;
                    return null;
                }
            }
        }
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] != null && elementData[i].equals(element)) {
                moveElementsAfterRemove(i);
                size--;
                return element;
            }
        }
        try {
            throw new NoSuchFieldException();
        } catch (NoSuchFieldException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] increaseSizeArray(T[] elementData) {
        return Arrays.copyOf(elementData, (elementData.length
                + elementData.length / 2));
    }

    private void moveElementsAfterRemove(int index) {
        if (elementData.length - 1 - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    elementData.length - 1 - index);
        }
    }
}
