package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == array.length) {
            grow();
        }
        // Зсуваємо всі елементи від index до кінця на одну позицію вправо
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List to add cannot be null");
        }
        if (list.isEmpty()) {
            return;
        }
        int requiredCapacity = size + list.size();
        if (array.length < requiredCapacity) {
            growTo(requiredCapacity);
        }
        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndexForAccess(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForAccess(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForAccess(index);
        final T removedValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = (int) (array.length * 1.5);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkIndexForAccess(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds. Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for add. Size: " + size);
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    private void growTo(int requiredCapacity) {
        int newCapacity = array.length;
        while (newCapacity < requiredCapacity) {
            newCapacity = (int) (newCapacity * 1.5);
        }

        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
