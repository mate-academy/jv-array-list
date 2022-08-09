package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementsArray;
    private int size;

    public ArrayList() {
        elementsArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growUp();
        elementsArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growUp();
        if (index == size) {
            add(value);
            return;
        }
        if (index < size && index >= 0) {
            System.arraycopy(elementsArray, index, elementsArray, index + 1, size - index);
            elementsArray[index] = value;
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
        return (T) elementsArray[index];
    }

    @Override
    public void set(T value, int index) {
        validationForIndex(index);
        if (index == size) {
            add(value);
        }
        elementsArray[index] = value;
    }

    @Override
    public T remove(int index) {
        validationForIndex(index);
        Object removedElement = elementsArray[index];
        System.arraycopy(elementsArray, index + 1, elementsArray, index, size - index - 1);
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementsArray.length; i++) {
            if (element != null
                    && element.equals(elementsArray[i])
                    || element == elementsArray[i]) {
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

    public void growUp() {
        if (elementsArray.length == size) {
            Object[] newArray = new Object[(int) (elementsArray.length + elementsArray.length / 2)];
            System.arraycopy(elementsArray,0,newArray,0, size);
            elementsArray = newArray;
        }
    }

    private void validationForIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is wrong for size " + size);
        }
    }
}
