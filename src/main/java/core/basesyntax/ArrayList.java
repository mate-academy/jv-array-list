package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T [] arr;
    private int size;
    private int currentCapacity = DEFAULT_CAPACITY;

    public ArrayList() {
        arr = (T[]) new Object [DEFAULT_CAPACITY];
    }

    public void increaseCapacity() {
        T[] old = arr;
        currentCapacity = currentCapacity + (currentCapacity >> 1);
        arr = (T[]) new Object[currentCapacity];
        for (int i = 0; i < size(); i++) {
            arr[i] = old[i];
        }
    }

    public int check(T element) {
        for (int i = 0; i < size; i++) {
            if ((arr[i] == element || (element != null && element.equals(arr[i])))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(T value) {
        add(value,size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        if (arr.length == size) {
            increaseCapacity();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }

        arr[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        int k = 0;
        while (list.size() + size() >= currentCapacity) {
            increaseCapacity();
        }
        for (int i = size(); i < size() + list.size();i++) {
            arr[i] = list.get(k++);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        return arr[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        T removeItem = arr[index];
        for (int i = index; i < size() - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return removeItem;
    }

    @Override
    public T remove(T element) {
        if (check(element) == -1) {
            throw new NoSuchElementException("");
        }
        return remove(check(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }
}
