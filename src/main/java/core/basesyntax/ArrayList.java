package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        checkCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            /*T o = list.get(i);
            checkCapacity();
            elementData[size] = o;
            size++;*/
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        /*final T removed = (T)elementData[index];
        Object[] firstPart = Arrays.copyOf(elementData, index);
        Object[] secondPart = Arrays.copyOfRange(elementData, index + 1, size);
        Object[] result = Arrays.copyOf(firstPart, firstPart.length + secondPart.length);
        System.arraycopy(secondPart, 0, result, firstPart.length, secondPart.length);
        elementData = result;
        size--;
        return removed;*/
        return removeElementAtIndex(index);
    }

    @Override
    public T remove(T element) {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < size; ++i) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                index = i;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Such an element doesn`t exist");
        }
        /*final T removed = (T)elementData[index];
        Object[] firstPart = Arrays.copyOf(elementData, index);
        Object[] secondPart = Arrays.copyOfRange(elementData, index + 1, size);
        Object[] result = Arrays.copyOf(firstPart, firstPart.length + secondPart.length);
        System.arraycopy(secondPart, 0, result, firstPart.length, secondPart.length);
        elementData = result;
        size--;
        return removed;*/
        return removeElementAtIndex(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return index;
    }

    /*public void checkCapacity() {
        if (elementData.length == size) {
            int newCapacity = elementData.length + (elementData.length / 2);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }*/
    public void checkCapacity() {
        if (elementData.length == size) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = elementData.length + (elementData.length / 2);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private T removeElementAtIndex(int index) {
        final T removed = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        size--;
        elementData[size] = null;
        return removed;
    }
}
