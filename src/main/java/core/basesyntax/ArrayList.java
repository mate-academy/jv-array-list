package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is"
                    + " larger than the array size");
        }
        resizeIfNeeded();

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        resizeIfNeeded();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is"
                    + " larger than the array size");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is"
                    + " larger than the array size");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is"
                    + " larger than the array size");
        }
        resizeIfNeeded();
        T element = elementData[index];

        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        boolean elementFound = false;

        for (int i = 0; i < size; i++) {
            if (elementData[i] == null || elementData[i] == element) {
                remove(i);
                elementFound = true;
                break;
            } else if (elementData[i].equals(element)) {
                remove(i);
                elementFound = true;
                break;
            }
        }

        if (!elementFound) {
            throw new NoSuchElementException("Element with such data was not found");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            int minCapacity = size + 1;
            T[] clonableObject = (T[]) new Object[newCapacity];

            if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                for (int i = 0; i < elementData.length; i++) {
                    clonableObject[i] = elementData[i];
                }
                elementData = clonableObject;
            } else {
                elementData = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
            }
        }
    }
}
