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
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
        elementData[size] = value;
        size = size + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of list's bound!!! Index: "
                    + index + ", Size: " + size);
        }
        final int s;
        if ((s = size) == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() < elementData.length) {
            for (int i = 0; i < list.size(); i++) {
                elementData[size] = list.get(i);
                size++;
            }
        } else {
            int newSize = elementData.length;
            do {
                newSize += BASE_SIZE;
            } while (newSize > size + list.size());
            Object[] tmpArr = Arrays.copyOf(elementData, newSize);
            for (int i = 0; i < list.size(); i++) {
                tmpArr[size + i] = list.get(i);
            }
            elementData = tmpArr;
            size += list.size();
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of list's bound!!! Index: "
                    + index + ", Size: " + size);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of list's bound!!! Index: "
                    + index + ", Size: " + size);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of list's bound!!! Index: "
                    + index + ", Size: " + size);
        }
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
        int index = 0;

        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                index = i;
                if (index >= size || index < 0) {
                    throw new ArrayListIndexOutOfBoundsException(
                            "Index out of list's bound!!! Index: " + index + ", Size: " + size);
                }
                final Object[] es = elementData;
                T oldValue = (T) es[index];
                final int newSize;
                if ((newSize = size - 1) > index) {
                    System.arraycopy(es, index + 1, es, index, newSize - index);
                }
                es[size = newSize] = null;
                return oldValue;

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
}
