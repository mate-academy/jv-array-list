package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int INITIALIZE_SIZE = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIALIZE_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            newCapacity();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            newCapacity();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        checkOutOfBounds(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBound(index);
        T elementToRemove = (T) elements[index];
        if (index != elements.length - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        elements[--size] = null;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element != null && element.equals(elements[i]) || element == elements[i]) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element doesn`t exist: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkOutOfBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound: " + index);
        }
    }

    private void newCapacity() {
        int oldSize = size;
        int newSize = oldSize + (oldSize >> 1);
        Object[] newArray = new Object[newSize];
        System.arraycopy(elements,0,newArray,0,size);
        elements = newArray;
    }
}
