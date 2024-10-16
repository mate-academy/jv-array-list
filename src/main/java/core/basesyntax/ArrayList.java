package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int initialCapacity = 16;
    private T[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.table = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        if (size == initialCapacity) {
            increaseArray();
        }
        table[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "non exist");
        } else if (size == initialCapacity) {
            increaseArray();
        }
        T[] table;
        int subSize = size;
        table = this.table;
        System.arraycopy(table, index, table, index + 1, subSize - index);
        size = subSize + 1;
        table[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index <= size - 1 && index >= 0) {
            return table[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "non exist");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "non exist");
        }
        table[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "non exist");
        } else {
            T[] table;
            table = this.table;
            final T removeValue = table[index];
            int subSize = size;
            System.arraycopy(table, index + 1, table, index, subSize - index - 1);
            size = subSize - 1;
            table[size] = null;
            return removeValue;
        }
    }

    @Override
    public T remove(T element) {
        T[] table;
        table = this.table;
        int subSize = size;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, table[i])) {
                System.arraycopy(table, i + 1, table, i, subSize - i - 1);
                size = subSize - 1;
                table[size] = null;
                return element;
            }
        }
        throw new NoSuchElementException("element " + element + "non exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void increaseArray() {
        int capacity = (int) (initialCapacity * 1.5);
        T[] currentArray = (T[]) new Object[capacity];
        System.arraycopy(table, 0, currentArray, 0, size);
        table = currentArray;
        initialCapacity = capacity;
    }
}

