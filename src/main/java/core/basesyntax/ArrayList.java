package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ARRAY_LENGTH = 10;
    private int size;
    private Object[] items;

    public ArrayList() {
        items = new Object[MAX_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size >= items.length) {
            growArray(size);
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (index == size) {
            add(value);
        } else {
            if (size + 1 > items.length) {
                growArray(size);
            }
            addInside(index, value);
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
        getIndexInRange(index);
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        getIndexInRange(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        getIndexInRange(index);
        Object removedElement = items[index];
        if (index + 1 != size) {
            System.arraycopy(items, index + 1, items, index, size - index);
        }
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == items[i])
                    || (element != null && element.equals(items[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void growArray(int countOfElements) {
        int newArrayLength = getNewLength(countOfElements);
        Object[] tempArray = new Object[newArrayLength];
        System.arraycopy(items, 0, tempArray, 0, items.length);
        items = new Object[newArrayLength];
        System.arraycopy(tempArray, 0, items, 0, tempArray.length);
    }

    private int getNewLength(int oldLength) {
        return oldLength * 3 / 2;
    }

    private void addInside(int index, T value) {
        Object[] tempArray = new Object[items.length - index - 1];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = items[index + i];
        }
        items[index] = value;
        for (int i = 0; i < tempArray.length; i++) {
            items[index + 1 + i] = tempArray[i];
        }
        size++;
    }

    private boolean getIndexInRange(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return true;
    }
}
