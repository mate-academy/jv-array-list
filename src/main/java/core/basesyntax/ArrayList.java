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
    
    @Override
    public void add(T value) {
        checkSizeAndGrow(size);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            size++;
            checkSizeAndGrow(size);
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
        checkValidIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkValidIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkValidIndex(index);
        T element = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index,
                elementData.length - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void checkSizeAndGrow(int size) {
        if (size == newDefaultCapacity) {
            newDefaultCapacity = (int) (elementData.length * 1.5);
            elementData = Arrays.copyOf(elementData, newDefaultCapacity);
        }
    }
    
    private void indexOutOfBounds(int index) {
        throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds array");
    }
    
    private int checkValidIndex(int index) {
        if (index >= 0 && index < size) {
            return index;
        } else {
            indexOutOfBounds(index);
        }
        return -1;
    }
}
