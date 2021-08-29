package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ARRAY_SIZE = 10;
    private int arrayListSize;
    private T[] listsOfElements = (T[]) new Object[MAX_ARRAY_SIZE];

    @Override
    public void add(T value) {
        if (arrayListSize == listsOfElements.length) {
            grow();
        }
        listsOfElements[arrayListSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arrayListSize) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound exception"
                    + index);
        }
        grow();
        System.arraycopy(listsOfElements, index, listsOfElements,
                index + 1, arrayListSize - index);
        listsOfElements[index] = value;
        arrayListSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) listsOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        listsOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = listsOfElements[index];
        final int newSize;
        if ((newSize = arrayListSize - 1) >= index) {
            System.arraycopy(listsOfElements, index + 1, listsOfElements, index,
                    newSize - index);
            listsOfElements[arrayListSize = newSize] = null;
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayListSize; i++) {
            if (listsOfElements[i] == element || (element != null
                    && element.equals(listsOfElements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element" + element);
    }

    @Override
    public int size() {
        return arrayListSize;
    }

    @Override
    public boolean isEmpty() {
        return arrayListSize == 0;
    }

    private T grow(int minCapacity) { // todo
        int newCapacity = listsOfElements.length + minCapacity;
        return (T) (listsOfElements = Arrays.copyOf(listsOfElements,
                newCapacity));
    }

    private T grow() {
        return grow(arrayListSize / 2);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= arrayListSize) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bound exception");
        }
    }
}
