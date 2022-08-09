package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final int DEFAULT_LIST_SIZE = 0;
    private Object[] mainArray;
    private int size;

    public ArrayList() {
        mainArray = new Object[DEFAULT_ARRAY_SIZE];
        size = DEFAULT_LIST_SIZE;
    }

    @Override
    public void add(T value) {
        if (size + 1 > mainArray.length) {
            sizeIncrease();
        }
        mainArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can`t add element to: "
                    + index + " index");
        }
        if (size >= mainArray.length) {
            validIndex(index);
        }
        if (size + 1 > mainArray.length) {
            sizeIncrease();
        }
        System.arraycopy(mainArray, index, mainArray, index + 1, size - index);
        mainArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validIndex(index);
        return (T) mainArray[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        mainArray[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        Object value = mainArray[index];
        System.arraycopy(mainArray, index + 1, mainArray, index, size - index - 1);
        size--;
        return (T) value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size + 1; i++) {
            if (element == mainArray[i] || (element != null && element.equals(mainArray[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element + " in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can`t set to " + index + " index");
        }
    }

    public void sizeIncrease() {
        Object[] temporallyArray = new Object[((int) (mainArray.length * 1.5))];
        System.arraycopy(mainArray, 0, temporallyArray, 0, size);
        mainArray = temporallyArray;
    }
}
