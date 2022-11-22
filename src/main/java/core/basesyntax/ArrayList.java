package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_LENGTH = 6;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        if (array.length <= size) {
            resize();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddByIndex(index);
        if (array.length <= size) {
            resize();
        }

        if (index < size) {
            System.arraycopy(array, index, array, index + 1, array.length - index - 1);
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
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return removeElementFromList(index);
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        return removeElementFromList(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T removeElementFromList(int index) {
        T result = array[index];
        if (index == size - 1) {
            size--;
            return result;
        }
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[size - 1] = null;
        size--;
        return result;
    }

    private int getIndex(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("List not contains this element");
    }

    private void checkIndexForAddByIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range List");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range List");
        }
    }

    private void resize() {
        int newSize = (int) (array.length * 1.5);
        array = Arrays.copyOf(array, newSize);
    }
}
