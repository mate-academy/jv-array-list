package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_SIZE = 10;
    private int size;
    private T[] values = (T[]) new Object[MAX_SIZE];

    @Override
    public void add(T value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 & index <= size) {
            resize();
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            ++size;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry received bad index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            values[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < size & index >= 0) {
            return values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry received bad index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size & index >= 0) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry this index is not exit");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size & index >= 0) {
            T obj = values[index];
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            values[size--] = null;
            return obj;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry this index is not exit");
        }
    }

    @Override
    public T remove(T element) {
        int index = 0;
        int flag = 0;
        T obj = (T) new Object();
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                index = i;
                flag = 1;
                obj = values[i];
            }
        }
        if (flag == 1) {
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            size--;
            values[size] = null;
        } else {
            throw new NoSuchElementException("Sorry this value does not exit");
        }
        return obj;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        values = Arrays.copyOf(values, values.length + 10);
    }
}
