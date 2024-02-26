package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INDEX_FOR_MESSAGE = "Index: ";
    private static final String SIZE_FOR_MESSAGE = ", Size:";

    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length || size > elementData.length) {
            newSizeForArray(size);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_FOR_MESSAGE
                    + index + SIZE_FOR_MESSAGE + size);
        }
        if (size == elementData.length || size > elementData.length) {
            newSizeForArray(size);
        }
        System.arraycopy(elementData,index, elementData,index + 1,size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elementData.length || size > elementData.length) {
            newSizeForArray(size);
        }
        for (int i = 0; i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_FOR_MESSAGE
                    + index + SIZE_FOR_MESSAGE + size);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_FOR_MESSAGE
                    + index + SIZE_FOR_MESSAGE + size);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_FOR_MESSAGE
                    + index + SIZE_FOR_MESSAGE + size);
        }
        final T removedElement = (T) elementData[index];
        System.arraycopy(elementData,index + 1, elementData,index,size - index - 1);
        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elementData[i] == null
                    || elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void newSizeForArray(int capacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (capacity >> 1);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData,0,newElementData,0,size);
        elementData = newElementData;
    }
}
