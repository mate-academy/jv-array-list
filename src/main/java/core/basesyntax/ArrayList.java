package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double ARRAY_INCREMENT_FACTOR = 1.5;
    private static final int DEFAULT_ARRAY_SIZE = 10;

    private Object[] elementArray;
    private int lastArrayIndex;

    public ArrayList() {
        this.elementArray = new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (lastArrayIndex == elementArray.length) {
            elementArray = grow(elementArray, elementArray.length);
        }
        elementArray[lastArrayIndex] = value;
        lastArrayIndex++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= lastArrayIndex) {
            if (lastArrayIndex < elementArray.length) {
                elementArray = addValueByIndex(elementArray, index, value);
            } else {
                elementArray = grow(elementArray, elementArray.length);
                elementArray = addValueByIndex(elementArray, index, value);
            }
            lastArrayIndex++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " out of bounds for length "
                    + (lastArrayIndex - 1)
            );
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newArraySize;
        if (list != null && !list.isEmpty()) {
            if (elementArray.length - lastArrayIndex < list.size()) {
                newArraySize = elementArray.length + list.size() - lastArrayIndex;
                elementArray = grow(elementArray, newArraySize);
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
            return (T) elementArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " out of bounds for length "
                    + (lastArrayIndex - 1)
            );
        }
    }

    @Override
    public void set(T value, int index) {
        if (arrayIncludeIndex(index)) {
            elementArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " out of bounds for length "
                    + (lastArrayIndex - 1)
            );
        }
    }

    @Override
    public T remove(int index) {
        Object temp;
        if (arrayIncludeIndex(index)) {
            temp = elementArray[index];
            elementArray = removeIndex(elementArray, index);
            lastArrayIndex--;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index
                    + " out of bounds for length "
                    + (lastArrayIndex - 1)
            );
        }
        return (T) temp;
    }

    @Override
    public T remove(T element) {
        Object temp;
        for (int i = 0; i < lastArrayIndex; i++) {
            if (deepElementCheck(i, element)) {
                temp = elementArray[i];
                elementArray = removeIndex(elementArray, i);
                lastArrayIndex--;
                return (T) temp;
            } else if (elementArray[i] == null && element == null) {
                elementArray = removeIndex(elementArray, i);
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

    private Object[] addValueByIndex(Object[] oldArray, int index, T value) {
        Object oldTemp = oldArray[index];
        Object[] newArray = new Object[oldArray.length];
        System.arraycopy(oldArray, 0, newArray, 0, index);
        newArray[index] = value;
        newArray[index + 1] = oldTemp;
        System.arraycopy(oldArray, index, newArray, index + 1, oldArray.length - index - 1);
        return newArray;
    }

    private Object[] grow(Object[] oldArray, int newArraySize) {
        int arraySize = (int) (newArraySize * ARRAY_INCREMENT_FACTOR);
        Object[] newArray = new Object[arraySize];
        System.arraycopy(oldArray, 0, newArray, 0, lastArrayIndex);
        return newArray;
    }

    private Object[] removeIndex(Object[] oldArray, int index) {
        Object[] newArray = new Object[elementArray.length];
        System.arraycopy(oldArray, 0, newArray, 0, index);
        System.arraycopy(oldArray, index + 1,
                newArray, index, oldArray.length - index - 1);
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
}
