package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double LIST_EXPAND = 0.5;
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new ArrayListIndexOutOfBoundsException("can't be 0 or less");
        }
        elementData = new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        checkSizeAndGrow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        checkSizeAndGrow();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object remove = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return (T) remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkSizeAndGrow() {
        if (elementData.length == size) {
            Object[] object = new Object[(int) (elementData.length
                    + elementData.length * LIST_EXPAND)];
            System.arraycopy(elementData,0,object,0,size);
            elementData = object;
        }
    }

    public void checkIndex(int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index can`t be more than size. Index = "
                    + index);
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can`t be less than 0. Index = "
                    + index);
        }
    }
}
