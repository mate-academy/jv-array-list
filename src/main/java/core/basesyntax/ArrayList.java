package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INDEX_EXCEPTION = "Index is incorrect";
    private static final String ELEMENT_EXCEPTION = "Can't find this element: ";
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION);
        }
        grow();
        T[] newElementData = (T[]) new Object[elementData.length];
        System.arraycopy(elementData, 0, newElementData, 0, index);
        newElementData[index] = value;
        System.arraycopy(elementData, index, newElementData, index + 1, size - index);
        elementData = newElementData;
        size += 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        T[] newElementData = (T[]) new Object[elementData.length];
        System.arraycopy(elementData, 0, newElementData, 0, index);
        newElementData[index] = value;
        System.arraycopy(elementData, index + 1, newElementData, index + 1, size - index);
        elementData = newElementData;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        final Object[] es = elementData;
        T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        if (element != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    remove(i);
                    return element;
                }
            }
        }
        if (element == null) {
            size--;
            return element;
        }
        throw new NoSuchElementException(ELEMENT_EXCEPTION + element);
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION);
        }
    }

    private void grow() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            if (oldCapacity > 0) {
                int newCapacity = elementData.length + (elementData.length >> 1);
                elementData = Arrays.copyOf(elementData, newCapacity);
            }
        }
    }
}
