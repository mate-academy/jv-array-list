package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String MESSAGE_INDEX_ERROR = "Index outside the size of the list";
    private static final String MESSAGE_NO_SUCH_ELEMENT_ERROR = "Element doesn`t exist";

    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_ERROR);
        }
        System.arraycopy(elementData, index, elementData, index + 1,
                elementData.length - index - 1);
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
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = (T) elementData[index];
        removeElement(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (elementData[index] == null
                    || (elementData[index] != null && elementData[index].equals(element))) {
                removeElement(elementData, index);
                return element;
            }
        }
        throw new NoSuchElementException(MESSAGE_NO_SUCH_ELEMENT_ERROR);
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_ERROR);
        }
    }

    private void removeElement(Object[] elementData, int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        elementData[--size] = null;
    }

    private void resize() {
        Object[] newSizeArray = new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, newSizeArray, 0, elementData.length);
        elementData = newSizeArray;
    }
}
