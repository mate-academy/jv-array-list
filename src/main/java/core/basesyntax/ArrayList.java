package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] values;

    private int size;

    public ArrayList() {
        values = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        growIfFullArray();
        addInList(value);
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            addInsideArray(value, index);
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
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return removeInArray(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element
                    || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("ArrayList do not contain " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void addInsideArray(T value, int index) {
        growIfFullArray();
        size++;
        System.arraycopy(values,index,values,index + 1, size - index);
        values[index] = value;
    }

    private void growIfFullArray() {
        if (size + 1 == values.length) {
            grow();
        }
    }

    private void addInList(T value) {
        values[size] = value;
        size++;
    }

    private void grow() {
        T[] grownArray = (T[]) new Object[(int) (values.length * 1.5)];
        System.arraycopy(values, 0, grownArray, 0, size);
        values = grownArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " was out of ArrayList bounds");
        }
    }

    private T removeInArray(int index) {
        final T removedValue = values[index];
        size--;
        System.arraycopy(values,index + 1,values,index,size - index);
        return removedValue;
    }
}
