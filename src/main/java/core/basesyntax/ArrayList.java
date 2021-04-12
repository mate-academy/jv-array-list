package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int capacity;
    private T[] array;
    private int size;

    public ArrayList() {
        this.capacity = 10;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (this.size == this.capacity) {
            this.capacity = this.capacity + this.capacity / 2;

        }
        T[] copyArray = (T[]) new Object[this.capacity];
        System.arraycopy(this.array, 0, copyArray, 0, this.array.length);
        this.array = (T[]) new Object[this.capacity];
        System.arraycopy(copyArray, 0, this.array, 0, copyArray.length);
        this.array[size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexCorrect(index, index > this.size);

        if (this.size == this.capacity) {
            this.capacity = this.capacity + this.capacity / 2;
        }

        T[] copyArray = (T[]) new Object[capacity];
        for (int i = 0; i < copyArray.length; i++) {
            if (i < index) {
                copyArray[i] = this.array[i];
            } else if (i == index) {
                copyArray[i] = value;
            } else {
                copyArray[i] = this.array[i - 1];
            }
        }
        this.array = copyArray;
        this.size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (this.array.length + list.size() < this.capacity) {
            this.capacity = this.capacity + this.capacity / 2;
            for (int i = 0; i < list.size(); i++) {
                this.array[this.size] = list.get(i);
                this.size++;
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                this.array[this.size] = list.get(i);
                this.size++;
            }
        }
    }

    @Override
    public T get(int index) {
        isIndexCorrect(index, index >= this.size);
        return this.array[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexCorrect(index, index >= this.size);
        this.array[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexCorrect(index, index > this.size);
        T removedElement = this.array[index];
        System.arraycopy(array, index + 1, array, index, this.size - index);
        this.size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(element, array[i])) {
                return this.remove(i);
            }
        }
        throw new NoSuchElementException("No such element here");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (this.size > 0) {
            return false;
        }
        return true;
    }

    private void isIndexCorrect(int index, boolean b) {
        if (b || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }
}
