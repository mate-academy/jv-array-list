package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        checkSize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkSize();
        if (index != size) {
            indexCheck(index);
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        indexCheck(index);
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        Objects.checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        Objects.checkIndex(index, size);
        T removedElement = (T) elementData[index];
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        elementData[size = newSize] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] ed = new Object[newCapacity];
        System.arraycopy(elementData, 0, ed, 0, size);
        elementData = ed;
    }

    private void checkSize() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
    }
}
