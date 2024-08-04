package core.basesyntax;

import java.util.NoSuchElementException;

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
        if (size >= elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (index == 0 && size == 0) {
            add(value);
            return;
        }
        if (size >= elements.length) {
            resize();
        }
        size++;
        T[] temp = (T[]) (new Object[elements.length]);
        System.arraycopy(elements, 0, temp, 0, index);
        temp[index] = value;
        System.arraycopy(elements, index, temp, index + 1, elements.length - index - 1);
        elements = (T[]) temp; //without this array does not resize properly.
    }

    @Override
    public void addAll(List<T> list) {
        int expectedLength = (list.size() + this.size());
        while (expectedLength > elements.length) {
            resize();
        }
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
        int index = -1;
        for (int i = 0; i < size; ++i) {
            if (element == elements[i]
                    || (element != null && element.equals(elements[i]))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("You're trying to remove element \""
                    + element + "\", that is not present in the list");
        }
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
        int newLength = (int) (elements.length * CAPACITY_INDEX);
        Object[] newArray = new Object[(int) newLength];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = (T[]) newArray;
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
