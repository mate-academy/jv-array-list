package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private int size;
    private T[] listArray;

    public ArrayList() {
        size = 0;
        listArray = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= listArray.length) {
            listArray = extendArray();
        }

        listArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }

        if (size + 1 >= listArray.length) {
            listArray = extendArray();
        }

        size++;

        T[] newArray = (T[]) new Object[listArray.length];
        for (int i = 0; i <= size; i++) {
            if (i < index) {
                newArray[i] = listArray[i];
            }

            if (i > index) {
                newArray[i] = listArray[i - 1];
            }
        }

        newArray[index] = value;
        listArray = newArray;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index);

        return listArray[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index);

        listArray[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index);

        T removedItem = listArray[index];
        for (int i = index + 1; i < size; i++) {
            listArray[i - 1] = listArray[i];
        }

        size--;

        return removedItem;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        boolean isElementInArray = false;
        for (int i = 0; i < size; i++) {
            if (listArray[i] == element || (listArray[i] != null && listArray[i].equals(element))) {
                removedElement = remove(i);
                isElementInArray = true;
                break;
            }
        }

        if (!isElementInArray) {
            throw new NoSuchElementException("No such element");
        }

        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private T[] extendArray() {
        return Arrays.copyOf(listArray, listArray.length + listArray.length / 2);
    }
}
