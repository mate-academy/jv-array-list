package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final int EXTENSION_DIVIDER = 2;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (this.size == this.array.length) {
            extendArray();
        }
        this.array[this.size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (this.size == this.array.length) {
            extendArray();
        }
        System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        this.array[index] = value;
        this.size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexForAccess(index);
        return this.array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForAccess(index);
        this.array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForAccess(index);
        final T value = this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
        this.size--;
        this.array[size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        int removeIndex = -1;
        for (int i = 0; i < this.size; i++) {
            T currentElement = this.array[i];
            if (currentElement == element
                    || currentElement != null
                    && currentElement.equals(element)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        final T value = this.array[removeIndex];
        System.arraycopy(
                this.array,
                removeIndex + 1,
                this.array,
                removeIndex,
                this.size - removeIndex - 1);
        this.size--;
        this.array[size] = null;
        return value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void extendArray() {
        T[] newArray = (T[]) new Object[this.array.length + this.array.length / EXTENSION_DIVIDER];
        System.arraycopy(this.array, 0, newArray, 0, this.size);
        this.array = newArray;
    }

    private void checkIndexForAccess(int index) {
        if (index >= this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index out of bounds: Index - " + index + ", Size - " + this.size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index out of bounds: Index - " + index + ", Size - " + this.size);
        }
    }
}
