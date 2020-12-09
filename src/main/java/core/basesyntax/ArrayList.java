package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_ARRAY = 10;
    private static final double COEFFICIENT_SIZE_ARRAY = 1.5;
    private T[] value;
    private int size;

    public ArrayList() {
        this.value = (T[]) new Object[SIZE_ARRAY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        widenArray();
        this.value[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        widenArray();
        System.arraycopy(this.value, index, this.value, index + 1, size - index);
        this.value[index] = value;
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
        if (index > size - 1 || index < 0) { // if we use a method checkIndex => +1 error;
            throw new ArrayIndexOutOfBoundsException("Houston we have a BUG!");
        }
        return value[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) { // if we use a method checkIndex => +1 error;
            throw new ArrayIndexOutOfBoundsException("Houston we have a BUG!");
        }
        this.value[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) value[index];
        System.arraycopy(this.value, index + 1, this.value, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (this.value[i] == t || t != null && t.equals(this.value[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void widenArray() {
        if (this.value.length == size) {
            T[] newArray = (T[]) new Object[(int) (value.length * COEFFICIENT_SIZE_ARRAY)];
            System.arraycopy(this.value,0, newArray, 0, size);
            this.value = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index > this.size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Houston we have a BUG!");
        }
    }
}
