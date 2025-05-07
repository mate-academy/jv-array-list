package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growUp();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (index < size && index >= 0) {
            growUp();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException(
                "Index " + index + " is wrong for size " + size);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validationForIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        validationForIndex(index);
        if (index == size) {
            add(value);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validationForIndex(index);
        Object removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element != null
                    && element.equals(elements[i])
                    || element == elements[i]) {
                return remove(i);

            }
        }
        throw new NoSuchElementException("No such element " + element + "at list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growUp() {
        if (elements.length == size) {
            Object[] newArray = new Object[(int) (elements.length + elements.length / 2)];
            System.arraycopy(elements,0,newArray,0, size);
            elements = newArray;
        }
    }

    private void validationForIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is wrong for size " + size);
        }
    }
}
