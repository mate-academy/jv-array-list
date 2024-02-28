package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double ARRAY_INCREMENT_FACTOR = 1.5;
    private static final int DEFAULT_ARRAY_SIZE = 10;

    private T[] elementArray;
    private int lastArrayIndex;

    public ArrayList() {
        this.elementArray = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (lastArrayIndex == elementArray.length) {
            elementArray = grow();
        }
        elementArray[lastArrayIndex] = value;
        lastArrayIndex++;
    }

    @Override
    public void add(T value, int index) {
        if (arrayIncludeIndex(index) || index == lastArrayIndex) {
            if (lastArrayIndex < elementArray.length) {
                elementArray = addValueByIndex(index, value);
            } else {
                elementArray = grow();
                elementArray = addValueByIndex(index, value);
            }
            lastArrayIndex++;
        } else {
            throwOutOfBoundsException(index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newArraySize;
        if (list != null && !list.isEmpty()) {
            if (elementArray.length - lastArrayIndex < list.size()) {
                newArraySize = elementArray.length + list.size() - lastArrayIndex;
                elementArray = grow(newArraySize);
            }
            for (int i = 0; i < list.size(); i++) {
                elementArray[lastArrayIndex] = list.get(i);
                lastArrayIndex++;
            }
        }
    }

    @Override
    public T get(int index) {
        if (arrayIncludeIndex(index)) {
            return elementArray[index];
        } else {
            throwOutOfBoundsException(index);
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (arrayIncludeIndex(index)) {
            elementArray[index] = value;
        } else {
            throwOutOfBoundsException(index);
        }
    }

    @Override
    public T remove(int index) {
        T temp = null;
        if (arrayIncludeIndex(index)) {
            temp = elementArray[index];
            elementArray = removeIndex(index);
            lastArrayIndex--;
        } else {
            throwOutOfBoundsException(index);
        }
        return temp;
    }

    @Override
    public T remove(T element) {
        T temp;
        for (int i = 0; i < lastArrayIndex; i++) {
            if (deepElementCheck(i, element)) {
                temp = elementArray[i];
                elementArray = removeIndex(i);
                lastArrayIndex--;
                return temp;
            } else if (elementArray[i] == null && element == null) {
                elementArray = removeIndex(i);
                lastArrayIndex--;
                return null;
            }
        }
        throw new NoSuchElementException("No such element in the array.");
    }

    @Override
    public int size() {
        return lastArrayIndex;
    }

    @Override
    public boolean isEmpty() {
        return lastArrayIndex == 0;
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
        System.arraycopy(elementArray, 0, newArray, 0, lastArrayIndex);
        return newArray;
    }

    private T[] grow(int newArraySize) {
        int arraySize = (int) (newArraySize * ARRAY_INCREMENT_FACTOR);
        T[] newArray = (T[]) new Object[arraySize];
        System.arraycopy(elementArray, 0, newArray, 0, lastArrayIndex);
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
        return index >= 0 && index < lastArrayIndex;
    }

    private boolean deepElementCheck(int i, T element) {
        return ((elementArray[i] != null
                && element != null && element.equals(elementArray[i]))
                && (element.hashCode() == elementArray[i].hashCode()));
    }

    private void throwOutOfBoundsException(int index) {
        throw new ArrayListIndexOutOfBoundsException("Index "
                + index
                + " out of bounds for length "
                + (lastArrayIndex - 1)
        );
    }
}
