package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_CAPACITY = 10;
    private int size = 0;
    private Object[] obj;

    public ArrayList() {
        obj = new Object[MAX_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == obj.length) {
            grow();
        }

        obj[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("going beyond array");
        }
        if (size == obj.length) {
            grow();
        }
        System.arraycopy(obj, index, obj, index + 1, size - index);
        obj[index] = value;
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
        checkValidIndex(index);
        return (T)obj[index];
    }

    @Override
    public void set(T value, int index) {
        checkValidIndex(index);
        obj[index] = value;
    }

    @Override
    public T remove(int index) {
        checkValidIndex(index);
        Object removed = obj[index];
        size--;
        System.arraycopy(obj, index + 1, obj, index, size - index);
        return (T)removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == obj[i]) || (element != null && element.equals(obj[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] data = (T[]) obj;
        obj = new Object[size + (size >> 1)];
        System.arraycopy(data, 0, obj, 0, size);
    }

    private void checkValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("going beyond array");
        }
    }
}
