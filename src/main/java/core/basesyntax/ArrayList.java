package core.basesyntax;

import java.util.Arrays;
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
        if (elementData.length == size) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        if (elementData.length == size) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listToArray = list.toArray();
        while (size + listToArray.length >= elementData.length) {
            elementData = grow();
        }
        System.arraycopy(listToArray, 0, elementData, size, listToArray.length);
        size += listToArray.length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        Objects.checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        Objects.checkIndex(index, size);
        final Object[] es = elementData;
        T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        final int size = this.size;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (es[i] == null) {
                    T removedElement = (T) es[i];
                    fastRemove(es, i);
                    return removedElement;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(es[i])) {
                    T removedElement = (T) es[i];
                    fastRemove(es, i);
                    return removedElement;
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

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public Object[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }
}
