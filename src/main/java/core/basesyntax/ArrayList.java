package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size] = value;
            size++;
        } else {
            resize();
            array[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bound for length %s", index, size));
        }
        if (size == array.length) {
            resizeByIndex();
        } else {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        array = new Object[size + list.size()];
        for (int i = 0; i < size; i++) {
            array[i] = this.array[i];
        }
        for (int i = 0; i < list.size(); i++) {
            array[size + i] = list.get(i);
        }
        size = array.length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bound for length %s", index, size)
            );
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bound for length %s", index, size)
            );
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bound for length %s", index, size));
        }
        Object deletedEl = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return (T) deletedEl;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == element)
                    || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeByIndex() {
        Object[] newArray = new Object[(int) (array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 1, size);
        array = newArray;
    }

    private void resize() {
        Object[] newArray = new Object[(int) (array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
