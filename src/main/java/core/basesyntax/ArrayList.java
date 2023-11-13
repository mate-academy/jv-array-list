package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double RESIZE = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[DEFAULT_SIZE];
    }

    public void modifiedCapacity() {
        if (size == array.length) {
            int modifiedSize = (int) (array.length * RESIZE);
            Object[] modifiedArray = new Object[modifiedSize];
            System.arraycopy(array, 0, modifiedArray, 0, size);
            array = modifiedArray;
        }
    }

    @Override
    public void add(T value) {
        modifiedCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct!" + index);
        }
        modifiedCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not  correct!" + index);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not  correct!" + index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not  correct!" + index);
        }
        Object removeOfElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return (T) removeOfElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Index is not exists" + element);
        }
        return remove(index);
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return i;
            }
        }
        return -1;
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
