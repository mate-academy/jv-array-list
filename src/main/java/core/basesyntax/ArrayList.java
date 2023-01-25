package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final int START_INDEX = 0;
    private static final int NEGATIVE_INDEX = -1;
    private static final int ADD_INDEX_NUMBER = 1;
    private int capacity;
    private T[] listT;
    private int size;

    public ArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.listT = (T[]) new Object[capacity];
        size = DEFAULT_SIZE;
    }

    @Override
    public void add(T value) {
        if (capacity > size) {
            listT[size] = value;
            size++;
            return;
        }
        increaseCapacity();
        listT[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (capacity > size) {
            addToIndex(value, index);
        } else {
            increaseCapacity();
            addToIndex(value, index);
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
        newArrayListIndexOutOfBoundsException(index);
        return listT[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= DEFAULT_SIZE) {
            listT[index] = value;
        }
        newArrayListIndexOutOfBoundsException(index);
    }

    @Override
    public T remove(int index) {
        return remove(get(index));
    }

    @Override
    public T remove(T element) {
        T[] newList = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            if (isEquals(listT[i], element)) {
                size--;
                System.arraycopy(listT, i + ADD_INDEX_NUMBER, newList, i, size - i);
                fullCopyArray(newList);
                return element;
            } else {
                newList[i] = listT[i];
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == DEFAULT_SIZE) {
            return true;
        }
        return false;
    }

    private void increaseCapacity() {
        capacity = capacity + (capacity >> ADD_INDEX_NUMBER);
        T[] newList = (T[]) new Object[capacity];
        System.arraycopy(listT, START_INDEX, newList, START_INDEX, listT.length);
        this.listT = (T[]) new Object[capacity];
        fullCopyArray(newList);
    }

    private void addToIndex(T value, int index) {
        T[] newList = (T[]) new Object[listT.length];
        if (index <= size && index > NEGATIVE_INDEX) {
            System.arraycopy(listT, START_INDEX, newList, START_INDEX, index + ADD_INDEX_NUMBER);
            newList[index] = value;
            System.arraycopy(listT, index, newList, index + ADD_INDEX_NUMBER, size - index);
            size++;
        }
        fullCopyArray(newList);
        newArrayListIndexOutOfBoundsException(index);
    }

    private boolean isEquals(T firstKey, T secondKey) {
        return firstKey == secondKey || (firstKey != null && firstKey.equals(secondKey));
    }

    private void newArrayListIndexOutOfBoundsException(int index) {
        if (index >= size || index < DEFAULT_SIZE) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index");
        }
    }

    private void fullCopyArray(T[] newList) {
        System.arraycopy(newList, START_INDEX, listT, START_INDEX, size);
    }
}
