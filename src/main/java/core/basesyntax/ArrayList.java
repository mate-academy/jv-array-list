package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }
    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!isIndexValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("message");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        for (int i = elementData.length - 1; i > index - 1; i--) {
            elementData[i] = elementData[i-1];
        }
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
        if (!isIndexValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("message");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("message");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!isIndexValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("message");
        }
        Object removedObject = elementData[index];
        for (int i = index; i < elementData.length - 1; i++) {
            elementData[i] =  elementData[i + 1];
        }
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        Object removedObject = null;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                removedObject = elementData[i];
                remove(i);
            }
        }
        throw new NoSuchElementException("message");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] grow = new Object[elementData.length + elementData.length >> 1];
        for (int i = 0; i < elementData.length; i++) {
            grow[i] = elementData[i];
        }
        return grow;
    }

    private boolean isIndexValid (int index) {
        return index > 0 && index < size;
    }
}
