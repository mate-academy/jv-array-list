package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elementDate;
    private int size;

    public ArrayList() {
        elementDate = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void checkIndexArrayList(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    private void checkIndexArrayListForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    @Override
    public void add(T value) {
        if (size == elementDate.length) {
            grow();
        }
        elementDate[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexArrayListForAdd(index);

        if (size == elementDate.length) {
            grow();
        }

        for (int i = size; i > index; i--) {
            elementDate[i] = elementDate[i - 1];
        }

        elementDate[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    private void grow() {
        int newArrayCapacity = elementDate.length + elementDate.length / 2;
        T[] newArray = (T[]) new Object[newArrayCapacity];
        System.arraycopy(elementDate, 0, newArray, 0, size);
        elementDate = newArray;
    }

    @Override
    public T get(int index) {
        checkIndexArrayList(index);
        return this.elementDate[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexArrayList(index);
        this.elementDate[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexArrayList(index);

        final T removedValue = this.elementDate[index];

        for (int i = index; i < size - 1; i++) {
            this.elementDate[i] = this.elementDate[i + 1];
        }
        size--;
        elementDate[size] = null;

        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementDate[i] == null)
                    || (element != null && element.equals(elementDate[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
