package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MEMORY_INCREASE_MULTIPLIER = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        sizeCheck();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        sizeCheck();
        if (index != size) {
            indexCheck(index);
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            sizeCheck();
            add(list.get(i));
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
        T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element don't present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] newArray = new Object[(int) (array.length * MEMORY_INCREASE_MULTIPLIER)];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    private void sizeCheck() {
        if (size == array.length) {
            array = grow();
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
