package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;
    public static final double GROW_FACTOR = 1.5;
    private Object[] elementsArray;
    private int size;

    public ArrayList() {
        elementsArray = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementsArray.length) {
            ensureCapacity();
        }
        elementsArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (isValidIndex(index)) {
            insertInetInExisting(index, value);
        } else if (index == size) {
            growToCapacity(elementsArray.length * 2);
            elementsArray[index] = value;
            size = index + 1;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void insertInetInExisting(int index, T value) {
        Object[] newElementsArray = new Object[elementsArray.length + 1];
        System.arraycopy(elementsArray, 0, newElementsArray, 0, index);
        newElementsArray[index] = value;
        System.arraycopy(elementsArray, index, newElementsArray,
                index + 1, elementsArray.length - index - 1);
        elementsArray = newElementsArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int requiredCapacity = size + list.size();
        if (requiredCapacity > elementsArray.length) {
            growToCapacity(requiredCapacity);
            for (int i = size, k = 0; i < requiredCapacity; i++, k++) {
                elementsArray[i] = list.get(k);
                size++;
            }
        } else {
            for (int i = size, k = 0; i < requiredCapacity; i++, k++) {
                elementsArray[i] = list.get(k);
            }
        }
    }

    @Override
    public T get(int index) {
        if (!isValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index - " + index);
        }
        return (T) elementsArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (isValidIndex(index)) {
            elementsArray[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Invalid index - " + index);
    }

    @Override
    public T remove(int index) {
        if (!isValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index - " + index);
        } else if (index == size - 1) {
            T temp = (T) elementsArray[index];
            elementsArray[index] = null;
            size--;
            return temp;
        } else {
            T temp = get(index);
            regroupArrayByRemove(index);
            return temp;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementsArray[i]
                    || (element != null
                    && element.equals(elementsArray[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element - " + element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        Object[] newElementsArray = new Object[(int) (elementsArray.length * GROW_FACTOR)];
        System.arraycopy(elementsArray, 0, newElementsArray, 0, elementsArray.length);
        elementsArray = newElementsArray;
    }

    private void growToCapacity(int newCapacity) {
        Object[] newElementsArray = new Object[newCapacity + 1];
        System.arraycopy(elementsArray, 0, newElementsArray, 0, elementsArray.length);
        elementsArray = newElementsArray;
    }

    private boolean isValidIndex(int index) {
        return size > index && index >= 0;
    }

    private void regroupArrayByRemove(int index) {
        Object[] newElementsArray = new Object[elementsArray.length];
        System.arraycopy(elementsArray, 0, newElementsArray, 0, index);
        System.arraycopy(elementsArray, index + 1, newElementsArray, index, size - index);
        elementsArray = newElementsArray;
        size--;
    }
}
