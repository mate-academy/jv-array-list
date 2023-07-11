package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_CONSTANT = 1.5;
    private Object[] elementData;
    private int size;
    private int newCapacity;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeCheck();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkForAdd(index);
        resizeCheck();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexOutOfBoundCheck(index);
        checkForAdd(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexOutOfBoundCheck(index);
        checkForAdd(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexOutOfBoundCheck(index);
        final T elementToRemove = (T) elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        elementData[size] = null;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (Objects.equals(element, elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds " + size);
        }
    }

    private void indexOutOfBoundCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds " + size);
        }
    }

    private void resizeCheck() {
        if (elementData.length < size + 1) {
            resize();
        }
    }

    private void resize() {
        newCapacity = (int) (elementData.length * RESIZE_CONSTANT);
        Object[] temp = elementData;
        elementData = new Object[newCapacity];
        System.arraycopy(temp,0,elementData,0,size);
    }
}
