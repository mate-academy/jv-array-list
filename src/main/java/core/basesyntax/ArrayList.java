package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASING_CONSTANT = 1.5;
    private int capacity;
    private int size;
    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    private T[] grow(int oldCapacity) {
        capacity = (int) (oldCapacity * INCREASING_CONSTANT);
        T[] newObjectsArray = (T[]) new Object[capacity];
        System.arraycopy(this.elementData, 0, newObjectsArray, 0, size);
        return newObjectsArray;
    }

    private void checkIndex(int index, boolean isIncludingSizeValue) {
        if (index > size || index < 0 || (!isIncludingSizeValue && index == size)) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is outside the list");
        }
    }

    private void expansion(int index) {
        System.arraycopy(this.elementData, index, elementData,
                index + 1, size - index);
    }

    private void compression(int index) {
        System.arraycopy(this.elementData, index + 1, this.elementData, index, size - index - 1);
        increaseSize(-1);
    }

    private void increaseSize(int value) {
        size += value;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            this.elementData = grow(size);
        }
        this.elementData[size] = value;
        increaseSize(1);
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        if (size == capacity) {
            this.elementData = grow(capacity);
        }
        expansion(index);
        this.elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }

        int newSize = this.size + list.size() - 1;
        if (newSize >= capacity) {
            this.elementData = grow(newSize);
        }

        for (int number = 0; number < list.size(); number++) {
            this.elementData[this.size] = list.get(number);
            increaseSize(1);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return this.elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T removed = this.elementData[index];
        compression(index);
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int number = 0; number < size; number++) {
            if (element == this.elementData[number]
                    || element != null && element.equals(this.elementData[number])) {
                return remove(number);
            }
        }
        throw new NoSuchElementException("The specified element does not exist");
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
