package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String ARRAY_OUT_OF_BOUND_EX = "This index is out of the array range...";
    private static final String NO_SUCH_ELEMENT_EX = "There is no such element...";
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T[] listToArray(List<T> list) {
        T[] listArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }
        return listArray;
    }

    private T[] reSizeArray() {
        T[] extendedArray = (T[]) new Object[(size * 3) / 2 + 1];
        System.arraycopy(elements, 0, extendedArray, 0, size);
        return extendedArray;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = reSizeArray();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (size == elements.length) {
            elements = reSizeArray();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_OUT_OF_BOUND_EX);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elements.length) {
            elements = reSizeArray();
        }
        T[] arrayFromList = listToArray(list);
        System.arraycopy(arrayFromList, 0, elements,
                size, arrayFromList.length);
        size += arrayFromList.length;
    }

    @Override
    public T get(int index) {
        if (inCorrectIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_OUT_OF_BOUND_EX);
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {

        if (inCorrectIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_OUT_OF_BOUND_EX);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (inCorrectIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_OUT_OF_BOUND_EX);
        }
        T deletedElementByIndex = elements[index];
        int numberOfElements = elements.length - (index + 1);
        System.arraycopy(elements, index + 1, elements, index, numberOfElements);
        size--;
        return deletedElementByIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element == elements[i] || element != null
                    && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_EX);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    public boolean inCorrectIndex(int index) {
        return (index >= size || index < 0);
    }
}
