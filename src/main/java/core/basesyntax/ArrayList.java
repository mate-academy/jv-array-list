package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double ARRAY_INCREMENT_FACTOR = 1.5;
    private static final int DEFAULT_ARRAY_SIZE = 10;

    private T[] elementArray;
    private int size;

    public ArrayList() {
        this.elementArray = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elementArray.length) {
            elementArray = grow();
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size || arrayIncludeIndex(index)) {
            if (size < elementArray.length) {
                elementArray = addValueByIndex(index, value);
            } else {
                elementArray = grow();
                elementArray = addValueByIndex(index, value);
            }
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newArraySize;
        if (list != null && !list.isEmpty()) {
            if (elementArray.length - size < list.size()) {
                newArraySize = elementArray.length + list.size() - size;
                elementArray = grow(newArraySize);
            }
            for (int i = 0; i < list.size(); i++) {
                elementArray[size] = list.get(i);
                size++;
            }
        }
    }

    @Override
    public T get(int index) {
        if (arrayIncludeIndex(index)) {
            return elementArray[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (arrayIncludeIndex(index)) {
            elementArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T temp = null;
        if (arrayIncludeIndex(index)) {
            temp = elementArray[index];
            elementArray = removeIndex(index);
            size--;
        }
        return temp;
    }

    @Override
    public T remove(T element) {
        T temp;
        for (int i = 0; i < size; i++) {
            if (deepElementCheck(i, element)) {
                temp = elementArray[i];
                elementArray = removeIndex(i);
                size--;
                return temp;
            } else if (elementArray[i] == null && element == null) {
                elementArray = removeIndex(i);
                size--;
                return null;
            }
        }
        throw new NoSuchElementException("No such element in the array.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] addValueByIndex(int index, T value) {
        T oldTemp = elementArray[index];
        T[] newArray = (T[]) new Object[elementArray.length];
        System.arraycopy(elementArray, 0, newArray, 0, index);
        newArray[index] = value;
        newArray[index + 1] = oldTemp;
        System.arraycopy(elementArray, index, newArray, index + 1, elementArray.length - index - 1);
        return newArray;
    }

    private T[] grow() {
        int arraySize = (int) (elementArray.length * ARRAY_INCREMENT_FACTOR);
        T[] newArray = (T[]) new Object[arraySize];
        System.arraycopy(elementArray, 0, newArray, 0, size);
        return newArray;
    }

    private T[] grow(int newArraySize) {
        int arraySize = (int) (newArraySize * ARRAY_INCREMENT_FACTOR);
        T[] newArray = (T[]) new Object[arraySize];
        System.arraycopy(elementArray, 0, newArray, 0, size);
        return newArray;
    }

    private T[] removeIndex(int index) {
        T[] newArray = (T[]) new Object[elementArray.length];
        System.arraycopy(elementArray, 0, newArray, 0, index);
        System.arraycopy(elementArray, index + 1,
                newArray, index, elementArray.length - index - 1);
        return newArray;
    }

    private boolean arrayIncludeIndex(int index) {
        if (index >= 0 && index < size) {
            return true;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " out of bounds for length "
                    + (size - 1)
            );
        }
    }

    private boolean deepElementCheck(int i, T element) {
        return ((elementArray[i] != null
                && element != null && element.equals(elementArray[i]))
                && (element.hashCode() == elementArray[i].hashCode()));
    }
}
