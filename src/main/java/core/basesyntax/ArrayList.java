package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int BASE_SIZE = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[BASE_SIZE];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); ++i) {
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
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findElement(element);
        remove(index);
        return element;
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
        if (size == elements.length) {
            int newLength = (int) (elements.length * CAPACITY_INDEX);
            Object[] newArray = new Object[(int) newLength];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = (T[]) newArray;
        }
    }

    private int findElement(T element) {
        int index;
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(element, elements[i])) {
                index = i;
                return index;
            }
        }
        throw new NoSuchElementException("You're trying to remove element \""
                + element + "\", that is not present in the list");
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such element in the list.");
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size + 1) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element to position "
                    + index + " in a list with size of " + size + " .");
        }
    }
}
