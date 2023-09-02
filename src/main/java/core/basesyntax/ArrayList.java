package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;
    private int size;
    private Object[] elementDate;

    public ArrayList() {
        this.elementDate = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    private Object[] grow() {
        capacity = (int) (capacity * 1.5);
        Object[] newObjectsArray = new Object[capacity];
        System.arraycopy(this.elementDate, 0, newObjectsArray, 0, size);
        return newObjectsArray;
    }

    private Object[] grow(int elements) {
        capacity = (int) (elements * 1.5);
        Object[] newObjectsArray = new Object[capacity];
        System.arraycopy(this.elementDate, 0, newObjectsArray, 0, size);
        return newObjectsArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is outside the list");
        }
    }

    private Object[] expansion(int index) {
        Object[] newObjectsArray = new Object[capacity];
        System.arraycopy(this.elementDate, 0, newObjectsArray, 0, index);
        System.arraycopy(this.elementDate, index, newObjectsArray,
                index + 1, size - index);
        return newObjectsArray;
    }

    private Object[] compression(int index) {
        Object[] newObjectsArray = new Object[capacity];
        System.arraycopy(this.elementDate, 0, newObjectsArray, 0, index);
        System.arraycopy(this.elementDate, index + 1, newObjectsArray, index, size - index - 1);
        increaseSize(-1);
        return newObjectsArray;
    }

    private void increaseSize(int value) {
        size += value;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            this.elementDate = grow();
        }
        this.elementDate[size] = value;
        increaseSize(1);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is outside the list");
        }
        if (size == capacity) {
            this.elementDate = grow();
        }
        this.elementDate = expansion(index);
        this.elementDate[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }

        int newSize = this.size + list.size() - 1;
        if (newSize >= capacity) {
            this.elementDate = grow(newSize);
        }

        for (int number = 0; number < list.size(); number++) {
            this.elementDate[this.size] = list.get(number);
            increaseSize(1);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) this.elementDate[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        this.elementDate[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) this.elementDate[index];
        this.elementDate = compression(index);
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int number = 0; number < size; number++) {
            if (element == this.elementDate[number]
                    || element != null && element.equals(this.elementDate[number])) {
                this.elementDate = compression(number);
                return element;
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
