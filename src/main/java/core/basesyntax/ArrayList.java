package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ELEMENTS = 10;
    private static final int INCREMENT_PERCENTS = 50;

    private int size;
    private Object[] list;

    public ArrayList() {
        size = 0;
        list = new Object[MAX_ELEMENTS];
    }

    @Override
    public void add(T value) {
        checkingListForCompl();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        checkingListForCompl();
        Object[] newArray = Arrays.copyOfRange(list, index, size);
        list = Arrays.copyOf(Arrays.copyOfRange(list, 0, index), list.length);
        size = index + 1;
        for (Object o : newArray) {
            list[size] = o;
            size++;
        }
        list[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > this.list.length) {
            checkingListForCompl(size);
        }
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                this.list[size] = list.get(i);
                size++;
            }
        }
    }

    private void checkingListForCompl(int size) {
        list = Arrays.copyOf(list, size * (100 + INCREMENT_PERCENTS) / 100);
    }

    private void checkingListForCompl() {
        if (size >= list.length) {
            checkingListForCompl(size);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can`t find element by index" + index);
        }
        return (T)list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can`t find element by index" + index);
        }
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        Object el = list[index];
        removing(index);
        return (T)el;
    }

    @Override
    public T remove(T element) {
        int index = findIndexByElement(element);
        if (index == -1) {
            throw new NoSuchElementException("Can`t find element int list");
        }
        removing(index);
        return element;
    }

    private void removing(int index) {
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        size--;
        list[size] = new Object();
    }

    private int findIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] == null && element == null
                    || list[i] != null && list[i].equals(element)) {
                return i;
            }
        }
        return -1;
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
