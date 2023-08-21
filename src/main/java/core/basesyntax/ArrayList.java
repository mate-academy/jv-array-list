package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int MAX_ARRAY_SIZE = 10;
    private int size;
    private Object[] objects;

    public ArrayList() {
        objects = new Object[MAX_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        grow();
        System.arraycopy(objects, index == 0 ? 0 : index - 1,
                objects, index == 0 ? 1 : index, size);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        objects = Arrays.copyOf(objects, size + list.size() + MAX_ARRAY_SIZE / 2);
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = (T)objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);

        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (objects[i] != null && objects[i].equals(element)
                    || objects[i] == null && element == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is out of range");
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
        if (size >= objects.length) {
            System.arraycopy(objects, 0, objects = new Object[size + MAX_ARRAY_SIZE / 2], 0, size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of or equals size " + size);
        }
    }
}
