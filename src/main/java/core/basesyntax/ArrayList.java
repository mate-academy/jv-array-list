package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] arr = new Object[DEFAULT_CAPACITY];
    private final int GROWTH_FACTOR = 2;
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == arr.length) {
            growIfNeeded();
        }
        arr[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index); // Проверяем индекс перед вставкой

        if (size == arr.length) {
            growIfNeeded();
        }

        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = value;
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
        return (T) arr[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) arr[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && arr[i] == null) || (arr[i] != null && arr[i].equals(element))) {
                T removedElement = (T) arr[i];
                removeElement(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeeded() {
        arr = Arrays.copyOf(arr, arr.length + (arr.length / GROWTH_FACTOR));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index for add: " + index);
        }
    }

    private void removeElement(int index) {
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        arr[--size] = null;
    }
}
