package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {

    private int defaltCapacity = 10;
    public Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = new Object[defaltCapacity];
    }

    private void grow() {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
    }

    private void checkRange(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void removeElement(Object[] element, int index) {
        int newSize = size - 1;
        if ((newSize) > index) {
            System.arraycopy(elementData, index + 1, elementData, index,newSize - index);
        }
        size = newSize;
        elementData[size] = null;
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkRange(index);
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
        checkRange(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
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
            }
        }

        if (oldValue == null) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return (T) oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
