package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double EXTENDS_KOEFFICIENT = 1.5;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Index out of bounds";
    private static final String ELEMENT_NOT_FOUND_MESSAGE = "Index out of bounds";
    private T[] array;
    private int size = 0;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.array = (T[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.array = (T[]) new Object[]{};
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        this.checkCapacityAndExtend();
        this.array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        this.checkCapacityAndExtend();
        if (index == size) {
            this.array[size] = value;
            size++;
        } else if (index < size && index >= 0) {
            for (int i = size - 1; i >= index; i--) {
                this.array[i + 1] = this.array[i];
            }
            this.array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return this.array[index];
        }
        throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            this.array[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            final T value = (T) this.array[index];
            System.arraycopy(this.array, index + 1, this.array, index, size - index - 1);
            this.array[size - 1] = null;
            size--;
            return value;
        }
        throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
    }

    @Override
    public T remove(T element) {
        int index = size;

        for (int i = 0; i < size; i++) {
            if (this.array[i] == element
                    || (this.array[i] != null && element != null
                    && this.array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(ELEMENT_NOT_FOUND_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacityAndExtend() {
        if (this.array.length == 0) {
            this.array = (T[]) new Object[DEFAULT_CAPACITY];
        } else if (this.array.length == size) {
            T[] defArray = (T[]) new Object[(int) (this.array.length * EXTENDS_KOEFFICIENT)];
            System.arraycopy(this.array, 0, defArray, 0, size);
            this.array = defArray;
        }
    }
}
