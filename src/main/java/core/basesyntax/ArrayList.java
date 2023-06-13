package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_COEFFICIENT = 1.5;
    private Object[] array;
    private Object[] tempArray;
    private int size = 0;

    public ArrayList(int capacity) {
        if (capacity > 0) {
            this.array = new Object[capacity];
        } else {
            this.array = new Object[DEFAULT_CAPACITY];
        }
    }

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (this.size + 1 > array.length) {
            resizeArray(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        } else if (index == size) {
            this.add(value);
        } else {
            tempArray = new Object[size - index];
            System.arraycopy(array, index, tempArray, 0, size - index);
            this.set(value, index);
            size = index + 1;
            for (Object tempArrayRow : tempArray) {
                this.add((T) tempArrayRow);
            }
        }

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T value;
        value = (T) array[index];

        for (int i = index + 1; i < array.length; i++) {
            array[i - 1] = array[i];
        }
        array[array.length - 1] = null;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        boolean isFound = false;
        for (int i = 0; i < array.length; i++) {
            if (this.equalsRow(array[i], element)) {
                remove(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            return element;
        } else {
            throw new NoSuchElementException("No such element " + element);
        }
    }

    private void resizeArray(Object[] array) {
        tempArray = array;
        this.array = new Object[(int) (array.length * RESIZE_COEFFICIENT)];
        System.arraycopy(tempArray, 0, this.array, 0, tempArray.length);
    }

    public <T> boolean equalsRow(T element1, T element2) {
        if (element1 == element2) {
            return true;
        }
        if (element1 == null || element2 == null) {
            return false;
        }
        if (element1.getClass().equals(element2.getClass())) {
            return element1.equals(element2);
        }
        return false;
    }

    public void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
