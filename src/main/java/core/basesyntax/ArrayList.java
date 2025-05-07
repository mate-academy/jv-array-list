package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist: " + index);
        }
        grow();
        changeArrayValues(value, index);
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
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        arrayList[--size] = null;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(arrayList[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element does not exist: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isEqual(T arrayElement, T element) {
        return arrayElement == element
                || (arrayElement != null && element != null
                && arrayElement.getClass().equals(element.getClass()))
                && arrayElement.equals(element);
    }

    private void grow() {
        if (size + 1 > arrayList.length) {
            T[] tempArray = (T[]) new Object[size + (size >> 1)];
            System.arraycopy(arrayList, 0, tempArray, 0, size);
            arrayList = tempArray;
        }
    }

    private void changeArrayValues(T value, int index) {
        T[] tempArray = (T[]) new Object[arrayList.length];
        System.arraycopy(arrayList, index, tempArray, 0, arrayList.length - index - 1);
        arrayList[index] = value;
        System.arraycopy(tempArray, 0, arrayList, index + 1, tempArray.length - index - 1);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist: " + index);
        }
    }
}
