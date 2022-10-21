package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private final int defaultSize = 5;
    private Object[] defaultNewCapacity;
    private T[] elementArray;
    private int size;

    public ArrayList() {
        elementArray = (T[]) new Object[defaultSize];
    }

    public ArrayList(int capacity) {
        elementArray = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        grow();
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(" Index "
                    + index + " out of bounds for length " + size);
        }
        defaultNewCapacity = new Object[size + 1];
        for (int i = 0; i < defaultNewCapacity.length; i++) {
            if (i < index) {
                defaultNewCapacity[i] = elementArray[i];
            }
            if (i == index) {
                defaultNewCapacity[i] = value;
            }
            if (i > index) {
                defaultNewCapacity[i] = elementArray[i - 1];
            }
        }
        size++;
        elementArray = (T[]) defaultNewCapacity;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() <= 0) {
            throw new IndexOutOfBoundsException("list " + list.size()
                    + " out of bounds for length " + size);
        }
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexSizeArray(index);
        return elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexSizeArray(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexSizeArray(index);
        defaultNewCapacity = new Object[size - 1];
        T result = get(index);
        for (int i = 0; i < defaultNewCapacity.length; i++) {
            if (i < index) {
                defaultNewCapacity[i] = elementArray[i];
            }
            if (result.equals(elementArray[i]) || i == index) {
                defaultNewCapacity[i] = elementArray[i + 1];
            }
            if (i > index) {
                defaultNewCapacity[i] = elementArray[i + 1];
            }
        }
        size--;
        elementArray = (T[]) defaultNewCapacity;
        return result;
    }

    @Override
    public T remove(T element) {
        int index = findIndexElement(element);
        if (index >= size || index < 0) {
            throw new NoSuchElementException("Element " + element
                    + " out of bounds for length " + size);
        }
        T result = elementArray[index];
        elementArray = (T[]) removeElementArray(element);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (elementArray.length == size) {
            defaultNewCapacity = new Object[elementArray.length * 2];
            System.arraycopy(elementArray, 0,
                    defaultNewCapacity, 0, size);
            elementArray = (T[]) defaultNewCapacity;
        }
    }

    private int findIndexElement(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elementArray[i] == null) {
                return i;
            } else if (element != null && element.equals(elementArray[i])) {
                return i;
            }
        }
        return -1;
    }

    private T removeElementArray(T element) {
        if (element == null) {
            size--;
            return null;
        } else {
            int activationFlag = 0;
            defaultNewCapacity = new Object[size];
            for (int i = 0; i < defaultNewCapacity.length; i++) {
                if (element.equals(elementArray[i])) {
                    defaultNewCapacity[i] = elementArray[i + 1];
                    activationFlag = 1;
                    size--;
                }
                defaultNewCapacity[i] = elementArray[i + activationFlag];
            }
            return (T) defaultNewCapacity;
        }
    }

    private void checkIndexSizeArray(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }
}

