package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int DEFAULT_CAPACITY = 10;
    private final double CAPACITY_INDEX = 1.5;
    private int size = 0;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        resize();
        System.arraycopy(values,0, values, 1,size);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new ArrayListIndexOutOfBoundsException("no list");
        }
        if (list.isEmpty()) {
            throw new ArrayListIndexOutOfBoundsException("the entered list is empty");
        }
        for (int i = 0; i < list.size(); i++) {
            resize();
            values[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object element;
        element = get(index);
        shiftToLeft(index);
        return (T) element;
    }

    @Override
    public T remove(T element) {
        if (size == 0) {
            throw new NoSuchElementException("the entered element is missing");
        }
        int i;
        for (i = 0; i < size; i++) {
            if (values[i] == null && element == null) {
                break;
            }
            if ((values[i] != null) && (values[i].equals(element))) {
                break;
            }
        }
        // when using the delete method with index here the exception is constantly thrown at line 85
        if (i < size) {
            shiftToLeft(i);
            return element;
        }
        throw new NoSuchElementException("the entered element is missing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void resize() {
        if (values.length == size) {
            int newCapacity = (int) (values.length * CAPACITY_INDEX);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void shiftToLeft(int start) {
        size--;
        if (size < 0) {
            return;
        }
        if (size != start) {
            System.arraycopy(values, start + 1, values, start, size - start);
        }
        values[size] = null;
    }
}
