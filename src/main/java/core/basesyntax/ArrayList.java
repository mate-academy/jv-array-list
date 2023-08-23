package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double PERCENTAGE_GROWTH = 1.5D;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkArraySize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isExistOrNext(index);
        checkArraySize();
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
        isExist(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        isExist(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isExist(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element missing: " + element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isExistOrNext(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element missing for given index "
                    + index);
        }
    }

    private void isExist(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element missing for given index "
                    + index);
        }
    }

    private void checkArraySize() {
        if (size + 1 < elements.length) {
            return;
        }
        Object[] newArray = new Object[(int) (elements.length * PERCENTAGE_GROWTH)];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}
