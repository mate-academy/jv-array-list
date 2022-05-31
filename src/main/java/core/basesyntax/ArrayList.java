package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] values;
    private int size = 0;

    public ArrayList() {
        values = (T[]) new Object[10];
    }

    private void isWrongIndex(int index)
            throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
    }

    private void grow() {
        if (size == values.length) {
            T[] tempArray = (T[]) new Object[(values.length + (values.length >> 1))];
            System.arraycopy(values, 0, tempArray, 0, size);
            values = tempArray;
        }
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
        grow();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        isWrongIndex(index);
        T removeValue = values[index];
        if (index != size - 1) {
            System.arraycopy(values, index + 1, values, index, size - index);
            values[size] = null;
        } else {
            values[index] = null;
        }
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element)
            throws ArrayListIndexOutOfBoundsException, NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (isEqual(element, values[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element was not found in List");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isEqual(T firstElement, T secondElement) {
        return (firstElement != null && firstElement.equals(secondElement))
                || firstElement == secondElement;
    }
}
