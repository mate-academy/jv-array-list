package core.basesyntax;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList(Collection<T> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            growIfFull();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        if (size + 1 > elementData.length) {
            growIfFull();
        }
        Object[] temp = new Object[elementData.length];
        System.arraycopy(elementData, 0, temp, 0, index);
        temp[index] = value;
        System.arraycopy(elementData, index, temp, index + 1, elementData.length - index - 1);
        size++;
        elementData = temp;
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() - (elementData.length - size) > 0) {
            growIfFull();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        T element = (T) elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index, elementData.length - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int foundIndex = -1;
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || element != null && element.equals(elementData[i])) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            throw new NoSuchElementException("No such element in the List");
        }
        return remove(foundIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFull() {
        int newSize = elementData.length + (elementData.length >> 1);
        Object[] temp = new Object[newSize];
        System.arraycopy(elementData, 0, temp, 0, elementData.length);
        this.elementData = temp;
    }
}
