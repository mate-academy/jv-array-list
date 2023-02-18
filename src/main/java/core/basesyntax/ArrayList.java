package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity;
    private T[] listArray;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.listArray = (T[]) new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, "Can't add to index " + index, true);
        if (size == currentCapacity) {
            resizeListArray(index);
        } else {
            if (size > 0) {
                System.arraycopy(listArray, index, listArray, index + 1, size - index);
            }
        }
        listArray[index] = value;
        size++;
    }

    private void checkIndex(int index, String message, boolean isInclusiveSize) {
        if (isInclusiveSize) {
            if (!(index >= 0 && index <= size)) {
                throw new ArrayListIndexOutOfBoundsException(message);
            }
        } else {
            if (!(index >= 0 && index < size)) {
                throw new ArrayListIndexOutOfBoundsException(message);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeListArray(int index) {
        currentCapacity = currentCapacity + (currentCapacity >> 1);
        T[] temp = (T[]) new Object[currentCapacity];
        System.arraycopy(listArray, 0, temp, 0, index);
        System.arraycopy(listArray, index, temp, index + 1, size - index);
        listArray = temp;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, "Can't get to index " + index, false);
        return listArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, "Can't set to index " + index, false);
        listArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, "Can't remove to index " + index, false);
        T temp = listArray[index];
        if (index < size - 1) {
            System.arraycopy(listArray, index + 1, listArray, index, size - index - 1);
        }
        listArray[--size] = null;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < listArray.length; i++) {
            if (Objects.equals(listArray[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array list");
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
