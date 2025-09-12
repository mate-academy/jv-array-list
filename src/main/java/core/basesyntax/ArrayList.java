package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int defaultCapacity = 10;
    private int capacity = defaultCapacity;
    private T[] arr = (T[])new Object[capacity];
    private int size = 0;

    @Override
    public void add(T value) {
        this.sizeValidation();

        this.arr[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index);
        }

        this.sizeValidation();

        System.arraycopy(this.arr, index, this.arr, index + 1, size - index);

        this.size++;
        this.arr[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        while (this.size + list.size() > capacity) {
            this.grow();
        }

        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        this.indexValidation(index);

        return this.arr[index];
    }

    @Override
    public void set(T value, int index) {
        this.indexValidation(index);

        this.arr[index] = value;
    }

    @Override
    public T remove(int index) {
        this.indexValidation(index);

        T elementToRemove = this.arr[index];

        System.arraycopy(this.arr, index + 1, this.arr, index, size - index - 1);

        this.arr[--size] = null;

        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int elementIndex = -1;

        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == null ? this.arr[i] == element : this.arr[i].equals(element)) {
                elementIndex = i;
                break;
            }
        }

        if (elementIndex == -1) {
            throw new NoSuchElementException("Index: " + elementIndex);
        }

        return this.remove(elementIndex);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void grow() {
        this.capacity = this.capacity + (this.capacity >> 1);
        T[] newArr = (T[])new Object[this.capacity];

        System.arraycopy(this.arr, 0, newArr, 0, this.size);

        this.arr = newArr;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index);
        }
    }

    private void sizeValidation() {
        if (this.size == capacity) {
            this.grow();
        }
    }
}
