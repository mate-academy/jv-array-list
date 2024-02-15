package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Array`s capacity cannot be <= 0");
        } else {
            array = (T[]) new Object[capacity];
            this.size = 0;
        }
    }

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index cannot be < 0 or > size");
        }
        checkCapacity();
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
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
        validateIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index cannot be less then 0");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index cannot be >= size");
        }
    }

    private void checkCapacity() {
        if (size == array.length) {
            int updatedCapacity = (int) (array.length * 1.5);
            T[] updatedArray = (T[]) new Object[updatedCapacity];
            for (int i = 0; i < array.length; i++) {
                updatedArray[i] = array[i];
            }
            array = updatedArray;
        }
    }
}
