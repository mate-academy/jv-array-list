package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int newDefaultCapacity = DEFAULT_CAPACITY;
    private T[] elementData;
    private int size = 0;
    
    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }
    
    public void grow(int size) {
        if (size == newDefaultCapacity) {
            newDefaultCapacity = (int) (elementData.length * 1.5);
            elementData = Arrays.copyOf(elementData, newDefaultCapacity);
        }
    }
    
    public void indexOutOfBounds(int index) {
        throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds array");
    }
    
    @Override
    public void add(T value) {
        grow(size);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            size++;
            grow(size);
            System.arraycopy(elementData, index,
                    elementData, index + 1,
                    elementData.length - index - 1);
            elementData[index] = value;
        } else {
            indexOutOfBounds(index);
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
        if (index < 0 || index >= size) {
            indexOutOfBounds(index);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elementData[index] = value;
        } else {
            indexOutOfBounds(index);
        }
    }

    @Override
    public T remove(int index) {
        T element = null;
        if (index >= 0 && index < size) {
            element = elementData[index];
            System.arraycopy(elementData, index + 1,
                    elementData, index,
                    elementData.length - index - 1);
            size--;
        } else {
            indexOutOfBounds(index);
        }
        return element;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        boolean foundValue = false;
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                index = i;
                foundValue = true;
                remove(index);
                break;
            }
        }
        if (foundValue == false) {
            throw new NoSuchElementException("No such element " + element);
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
}
