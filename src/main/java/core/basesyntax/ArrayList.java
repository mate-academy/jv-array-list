package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double SIZE_MULTIPLIER = 1.5;
    private static final String INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE
            = "Invalid index value! Actual list size is: ";
    private static final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE
            = "Element not found! Invalid value!";
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = createListContainer(INITIAL_CAPACITY);
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            indexValidation(index);
            shiftRight(index);
            elementData[index] = value;
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
        indexValidation(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T deletedElement = elementData[index];
        shiftLeft(index);
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        return remove(indexOf(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * SIZE_MULTIPLIER);
            T[] newElementData = createListContainer(newCapacity);
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
    }

    private void shiftRight(int index) {
        grow();
        System.arraycopy(elementData, index, elementData, index + 1, ++size - index - 1);
    }

    private void shiftLeft(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, --size - index);
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value == get(i) || (value != null && value.equals(get(i)))) {
                return i;
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE + size
            );
        }
    }

    @SuppressWarnings("unchecked")
    private T[] createListContainer(int capacity) {
        return (T[]) new Object[capacity];
    }
}
