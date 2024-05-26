package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPASITY = 10;
    private static final int NOT_FOUND = -1;
    private static final int GROW_FACTOR = 2;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPASITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (invalidIndexToAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of range index");
        }
        if (needToGrow()) {
            grow();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
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
        checkValidIndexToGetSet(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkValidIndexToGetSet(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = find(element);
        if (index == NOT_FOUND) {
            throw new NoSuchElementException("Element " + element + " was not found");
        }
        return remove(index);
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
        T[] newArr = resize();
        System.arraycopy(array, 0, newArr, 0, size);
        array = newArr;
    }

    private T[] resize() {
        int newLength = size + size / GROW_FACTOR;
        return (T[]) new Object[newLength];
    }

    private int find(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void checkValidIndexToGetSet(int index) {
        if (invalidIndexToSetGet(index)) {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of range index");
        }
    }

    private boolean invalidIndexToAdd(int index) {
        if (index == size) {
            return false;
        }
        return invalidIndexToSetGet(index);
    }

    private boolean invalidIndexToSetGet(int index) {
        return index < 0 || index >= size;
    }

    private boolean needToGrow() {
        return size == array.length;
    }
}
