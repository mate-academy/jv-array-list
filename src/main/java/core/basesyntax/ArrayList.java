package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final double ARRAY_ADD_SIZE = 1.5;
    private Object[] values = new Object[ARRAY_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        checkingResize();
        this.values[this.size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkingResize();
        if (index != size) {
            exceptionCheck(index);
        }
        if (index <= size) {
            Object[] newArray = this.values.clone();
            values[index] = value;
            for (int i = index + 1; i <= size; i++) {
                this.values[i] = newArray[i - 1];
            }
            this.size++;
        } else {
            this.values[this.size] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        exceptionCheck(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        exceptionCheck(index);
        this.values[index] = value;
    }

    @Override
    public T remove(int index) {
        exceptionCheck(index);
        Object deleted = values[index];
        for (int i = index; i < size; i++) {
            if (index == size - 1) {
                this.values[i] = null;
            } else {
                this.values[i] = values[i + 1];
            }
        }
        this.size--;
        return (T) deleted;
    }

    @Override
    public T remove(T element) {
        Object deleted = null;
        boolean isElement = false;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (values[i] == null && element == null
                    || values[i] != null && values[i].equals(element)) {
                isElement = true;
                index = i;
                deleted = values[i];
                break;
            }
        }
        if (isElement) {
            exceptionCheck(index);
            for (int i = index; i < size; i++) {
                if (index == size - 1) {
                    this.values[i] = null;
                } else {
                    this.values[i] = values[i + 1];
                }
            }
            this.size--;
        } else {
            throw new NoSuchElementException("No such element in Array");
        }

        return (T) deleted;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public void exceptionCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index of Array is out of bounds");
        }
    }

    public void checkingResize() {
        if (this.values.length == size) {
            values = Arrays.copyOf(values, (int) (size * ARRAY_ADD_SIZE));
        }
    }
}
