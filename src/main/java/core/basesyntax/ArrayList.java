package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    private static final int INITIAL_SIZE = 0;
    private static final double EXPANDED_SIZE = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[INITIAL_ARRAY_SIZE];
        this.size = INITIAL_SIZE;
    }

    @Override
    public void add(T value) {
        expandCapacityIfArrayFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            expandCapacityIfArrayFull();
            for (int i = size - 1; i >= index; i--) {
                elementData[i + 1] = elementData[i];
            }
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("There is no index: " + index);
        }
    }

    private void expandCapacityIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * EXPANDED_SIZE);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); ++i) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("There is no index: " + index);
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index <= size - 1) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("There is no index: " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T removedValue = (T) elementData[index];
            for (int i = index; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            size--;
            return removedValue;
        } else {
            throw new ArrayListIndexOutOfBoundsException("There is no index: " + index);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; ++i) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                T removedElement = (T) elementData[i];
                for (int j = i; j < size - 1; ++j) {
                    elementData[j] = elementData[j + 1];
                }
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("There's no element" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
