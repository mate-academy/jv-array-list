package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASE_SIZE = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[BASE_SIZE];
    }

    @Override
    public void add(T value) {
        checkLength();
        elementData[size] = value;
        size = size + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            final int s = size;
            checkLength();
            System.arraycopy(elementData, index, elementData, index + 1, s - index);
            elementData[index] = value;
            size = s + 1;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of list's bound!!! Index: "
                    + index + ", Size: " + size);
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
        final Object[] es = elementData;
        T oldValue = (T) es[index];
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        es[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
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

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of list's bound!!! Index: "
                    + index + ", Size: " + size);
        }
    }

    private void checkLength() {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 3 / 2);
        }
    }
}
