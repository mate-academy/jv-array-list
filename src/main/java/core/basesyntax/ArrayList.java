package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_NUMBER = 10;
    private Object[] data;
    private int size;

    public ArrayList(int number) {
        if (number <= 0) {
            throw new ArrayListIndexOutOfBoundsException("Element can't be zero or less");
        }
        data = new Object[number];
    }

    public ArrayList() {
        this(MAX_NUMBER);
    }

    @Override
    public void add(T value) {
        overrideMethod();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        overrideIndex(index);
        overrideMethod();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        overrideIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        overrideIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        overrideIndex(index);
        Object removeData = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return (T) removeData;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element not found " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void overrideMethod() {
        if (data.length == size) {
            Object[] object = new Object[data.length + (data.length << 1)];
            System.arraycopy(data, 0, object, 0, size);
            data = object;
        }
    }

    private void overrideIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index is more than size");
        }
    }
}
