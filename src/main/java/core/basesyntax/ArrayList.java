package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    public Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length + (elementData.length / 2));
        }
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void removeElement(Object[] element, int index) {
        checkIndexRange(index);
        int newSize = size - 1;
        if ((newSize) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        size = newSize;
        elementData[size] = null;
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexRange(index);
        grow();
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
        checkIndexRange(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRange(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        Object oldValue = elementData[index];
        removeElement(elementData, index);
        return (T) oldValue;
    }

    @Override
    public T remove(T t) {
        Object oldValue = null;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(t)) {
                oldValue = elementData[i];
                removeElement(elementData, i);
                return (T) oldValue;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
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
