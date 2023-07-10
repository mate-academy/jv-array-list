package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private Object[] elementData = new Object[INITIAL_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        ensureArrayCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureArrayCapacity();
        checkIndex(index, size);
        System.arraycopy(elementData, index,
                elementData, index + 1, size - index);
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
        checkIndex(index, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size - 1);
        T removedElement = (T) elementData[index];
        size--;
        System.arraycopy(elementData,
                index + 1, elementData, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element as " + element + "in "
                + "ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureArrayCapacity() {
        if (size >= elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0,
                    newElementData, 0, oldCapacity);
            this.elementData = newElementData;
        }
    }

    private void checkIndex(int index, int size) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is outside the array , size" + " is: " + size);
        }
    }
}
