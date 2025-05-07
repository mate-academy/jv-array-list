package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object [DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of bound when size: " + size);
        }
        grow(size + 1);
        System.arraycopy(elementData, index, elementData,
                index + 1, elementData.length - index - 1);
        size++;
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        grow(elementData.length + 1);
        T value = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            return remove(index);
        }
        throw new NoSuchElementException("Element: " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Non existed position "
                    + index + " when size is: " + size);
        }
    }

    private int indexOf(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void grow() {
        if (size == elementData.length) {
            grow(size + 1);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (minCapacity - oldCapacity) + (oldCapacity >> 1);
        T[] increasedElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, increasedElementData, 0, size);
        elementData = increasedElementData;
    }
}


