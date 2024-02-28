package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INDEX_ERROR_MESSAGE =
            "Index: [%d] is out of bounds for list size [%d]!";
    private static final String NO_SUCH_EL_ERR_MES = "Can't add element: [%s]";

    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length || size > elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_ERROR_MESSAGE, index, size));
        }
        if (size == elementData.length || size > elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growToCapacity(list.size());
        for (int i = 0; i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isLegalIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isLegalIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isLegalIndex(index);
        final T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
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
        throw new NoSuchElementException(String.format(NO_SUCH_EL_ERR_MES, element));
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
        int newCapacity = oldCapacity + (size >> 1);
        copyArr(newCapacity);
    }

    private void growToCapacity(int needCapacity) {
        int newCapacity = size + needCapacity;
        copyArr(newCapacity);
    }

    private void copyArr(int newCapacity) {
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private void isLegalIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_ERROR_MESSAGE, index, size));
        }
    }
}
