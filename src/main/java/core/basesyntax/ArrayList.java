package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int VALUE_ARRAY = 10;
    private T[] elementData = (T[]) new Object[VALUE_ARRAY];
    private int size;

    @Override
    public void add(T value) {
        if (size + 1 < elementData.length) {
            elementData[size++] = value;
        } else {
            T[] newArray = (T[]) new Object[changeLength()];
            System.arraycopy(elementData, 0, newArray, 0, size);
            newArray[size++] = value;
            elementData = newArray;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (index < elementData.length) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            if (index >= elementData.length) {
                T[] copyElementData = (T[]) new Object[changeLength()];
                System.arraycopy(elementData, 0, copyElementData, 0, index - 1);
                copyElementData[index] = value;
                System.arraycopy(elementData, index, copyElementData, index + 1, size - index);
                size++;
                elementData = copyElementData;
            }
        } else {
            add(value);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        Object removeElement;
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        removeElement = elementData[index];
        int needNumberCopy = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, needNumberCopy);
        elementData[--size] = null;
        return (T) removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int changeLength() {
        return elementData.length
                + (elementData.length >> 1);
    }
}
