package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE = 10;
    public static final double DEFAULT_MULT = 1.5;
    public Object[] test;
    private int size;

    public ArrayList() {
        test = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int initialCapacity) {
        test = new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        if (test.length < size + 2) {
            test = Arrays.copyOf(test, (int) (test.length * DEFAULT_MULT));
        }
        test[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (test.length < size + 2) {
            test = Arrays.copyOf(test, (int) (test.length * DEFAULT_MULT));
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("" + index);
        }
        size++;
        for (int i = size - 1; i > index; i--) {
            test[i] = test[i - 1];
        }
        test[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        chekIndex(index);
        return (T) test[index];
    }

    @Override
    public void set(T value, int index) {
        chekIndex(index);
        test[index] = value;
    }

    @Override
    public T remove(int index) {
        chekIndex(index);
        T value = (T) test[index];
        for (int i = index; i < size; i++) {
            test[i] = test[i + 1];
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        boolean check = true;
        if (element == null) {
            size--;
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (test[i] == null) {
                check = false;
                continue;
            }
            if (test[i].equals(element)) {
                remove(i);
                check = false;
                break;
            }
        }
        if (check) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void chekIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("" + index);
        }
    }
}