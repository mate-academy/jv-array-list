package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private static final String INDEX_OUT_OF_BOUNDS = "Index is out of bounds";
    private static final String NO_SUCH_ELEMENT = "No %s in array list";
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (elements.length - size < list.size()) {
            grow();
        }
        T[] arrayOfIncomeElements = toArray(list);
        System.arraycopy(arrayOfIncomeElements, 0, elements, size, arrayOfIncomeElements.length);
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkSetGetRemoveIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkSetGetRemoveIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkSetGetRemoveIndex(index);
        T oldElementValue = elements[index];
        if (size - 1 > index) {
            System.arraycopy(elements, index + 1, elements, index, size - 1);
        } else {
            elements[index] = null;
        }
        size--;
        return oldElementValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = 0;
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null && elements[i].equals(element))
                    || (elements[i] == null && element == null)) {
                indexOfElement = i;
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT, element.toString()));
        }
        return remove(indexOfElement);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] toArray(List<T> list) {
        T[] resulArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resulArray[i] = list.get(i);
        }
        return resulArray;
    }

    private void grow() {
        int newLength = (int) (elements.length * GROWTH_COEFFICIENT);
        elements = Arrays.copyOf(elements, newLength);
    }

    private void checkAddIndex(int index) {
        if ((!isEmpty() && index > size) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
    }

    private void checkSetGetRemoveIndex(int index) {
        if ((!isEmpty() && index >= size) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
    }
}
