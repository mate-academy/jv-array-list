package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_CAPACITY = 10;
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_SIZE_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            arrayCopy();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
        if (size == elements.length) {
            arrayCopy();
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element == null
                    || elements[i] != null && elements[i].equals(element)) {
                size--;
                System.arraycopy(elements, i + 1, elements, i, size - i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element in this array" + element);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist " + index);
        }
    }

    private void grow() {
        int newCapacity = (int) (elements.length * MAGNIFICATION_FACTOR);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0,
                newArray, 0, size);
        elements = (T[]) newArray;
    }

    private void arrayCopy() {
        T[] someDataCopy = (T[]) new Object[(int) (elements.length * MAGNIFICATION_FACTOR)];
        System.arraycopy(elements,0,someDataCopy, 0, elements.length);
        elements = someDataCopy;
    }

}
