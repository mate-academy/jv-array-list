package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private Object[] arr = new Object[capacity];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == capacity) {
            resize();
        }
        arr[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (!isValidIndexForAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        if (size == capacity) {
            resize();
        }
        // Вставка сдвигом элементов вправо
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > capacity) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            arr[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (!isValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        return (T) arr[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!isValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        T removedElement = (T) arr[index];
        // Сдвиг элементов влево для удаления
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isValidIndexForAdd(int index) {
        return index >= 0 && index <= size;
    }

    private void resize() {
        capacity = capacity * 3 / 2 + 1;
        Object[] newArr = new Object[capacity];
        // Копируем элементы вручную
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
}
