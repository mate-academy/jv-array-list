package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private Object[] elements;
    private int arraySize;

    public ArrayList() {
        elements = new Object[ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (arraySize == elements.length) {
            elements = resize();
        }
        elements[arraySize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index of bound exception");
        }
        if (arraySize == elements.length) {
            elements = resize();
        }
        System.arraycopy(elements,index, elements,index + 1, arraySize - 1);
        elements[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, arraySize - index);
        arraySize--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private T[] resize() {
        T[] array = (T[])new Object[(arraySize * 3) / 2 + 1];
        System.arraycopy(elements, 0, array, 0, arraySize);
        return array;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index of bound exception");
        }
    }
}
