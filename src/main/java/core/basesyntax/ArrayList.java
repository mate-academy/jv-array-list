package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private T[] elementsData;

    public ArrayList() {
        elementsData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementsData.length) {
            resize();
        }
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (index == size) {
            add(value);
        } else {
            if (size == elementsData.length) {
                resize();
            }
            System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
            elementsData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexEqualSize(index);
        return elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexEqualSize(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexEqualSize(index);
        T removedElement = elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, (size - index) - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementsData[i] == element || elementsData[i] != null
                    && elementsData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Does not have an element with such a value " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {

        elementsData = Arrays.copyOf(elementsData, (int) (elementsData.length * 1.5 + 1));
    }

    private void checkIndexEqualSize(int index) {
        if (index >= size || index < 0) {
            throwException(index);
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throwException(index);
        }
    }

    private void throwException(int index) {
        throw new ArrayListIndexOutOfBoundsException("Invalid index outside"
                + " the dimension of the array, index - " + index + System.lineSeparator()
                + "array size - " + size);
    }
}
