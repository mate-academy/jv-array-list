package core.basesyntax;

import java.util.Objects;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] array;
    private int size;
    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    @Override
    public void add(T value) {
        ensureCapacity();
        array[size++] = value;
    }
    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();

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
        T removedValue = array[index];

        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedValue;
    }
    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i],element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Елемент '" + element + "' не знайдено у списку");
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = (int) (array.length * GROWTH_FACTOR);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Невірний індекс: " + index);
        }
    }
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Невірний індекс: " + index);
        }
    }
}

