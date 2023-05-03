package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size < DEFAULT_SIZE) {
            elementData[size++] = value;
        } else {
            grow();
            elementData[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size < DEFAULT_SIZE) {
            arrayCopyForAddByIndex(index);
            elementData[index] = value;
            size++;
        } else {
            grow();
            arrayCopyForAddByIndex(index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (elementData.length > list.size()) {
            for (int i = 0; i < list.size(); i++) {
                elementData[size++] = list.get(i);
            }
        } else {
            grow();
            addAll(list);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        if (size > index) {
            return elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Error in get method");
        }
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        if (size > index) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Error in set method");
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (size > index) {
            T oldValue = elementData[index];
            removeByIndex(elementData, index);
            return oldValue;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Error in remove by index method");
        }
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < elementData.length; index++) {
            if (equalsElemets(elementData[index], element)) {
                removeByIndex(elementData, index);
                return element;
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

    private T[] grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != null) {
            int newCapacity = oldCapacity + (DEFAULT_SIZE >> 1) + 1;
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            throw new ArrayListIndexOutOfBoundsException("Error in grow method");
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index > size or index < 0");
        }
    }

    private void arrayCopyForAddByIndex(int index) {
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    private void removeByIndex(T[] elementData, int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
    }

    private boolean equalsElemets(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
