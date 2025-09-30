package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double EXTENSION_COEFFICIENT = 1.5;
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
        if (index > this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (this.size == this.array.length) {
            extendArray();
        }
        for (int i = this.size; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
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
        if (index >= this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return this.array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        this.array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        T value = this.array[index];
        shiftLeft(index);
        return value;
    }

    @Override
    public T remove(T element) {
        int removeIndex = -1;
        for (int i = 0; i < this.size; i++) {
            T currentElement = this.array[i];
            if (currentElement == element || currentElement != null && currentElement.equals(element)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == -1) {
            throw new NoSuchElementException("Element not found");
        }
        T value = this.array[removeIndex];
        shiftLeft(removeIndex);
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

    public void extendArray() {
        T[] newArray = (T[]) new Object[(int) (this.array.length * EXTENSION_COEFFICIENT)];
        for (int i = 0; i < this.size; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    public void shiftLeft(int index) {
        for (int i = index; i < this.array.length - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.size--;
    }
}
