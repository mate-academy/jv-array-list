package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int currentCapacity = DEFAULT_CAPACITY;
    private int size = 0;

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        checkSizeForAdd(index);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() >= currentCapacity - list.size()) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkForBoundsException(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkForBoundsException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForBoundsException(index);
        final T oldElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = 0;
        size--;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                int index = i;
                final T oldElement = (T) elementData[i];
                System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
                elementData[size - 1] = 0;
                size--;
                return oldElement;
            }
        }
        throw new NoSuchElementException("No such Element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] grow() {
        currentCapacity += currentCapacity >> 1;
        Object[] newElementData = new Object[currentCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    public void checkSizeForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }

    public void ensureCapacity() {
        if (size == currentCapacity) {
            elementData = grow();
        }
    }

    public void checkForBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid");
        }
    }
}
