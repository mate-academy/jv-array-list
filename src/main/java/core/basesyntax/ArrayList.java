package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_ARRAY_LENGTH = 10;
    private int size;
    private T[] objects;

    public ArrayList() {
        objects = (T[]) new Object[START_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        doNeedGrowth();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index does not exist: " + index);
        }
        doNeedGrowth();
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
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
        validate(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        validate(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        validate(index);
        T removed = objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - 1 - index);
        objects[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        size--;
        for (int j, i = 0; i < size; i++) {
            if ((objects[i] == element) || (objects[i] != null && objects[i].equals(element))) {
                System.arraycopy(objects, i + 1, objects, i, size - i);
                return element;
            }
        }
        throw new NoSuchElementException("Can`t find element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void doNeedGrowth() {
        if (objects.length == size) {
            grow();
        }
    }

    private void grow() {
        Object [] copied = new Object[(int) (objects.length * 1.5)];
        System.arraycopy(objects, 0, copied, 0, size);
        objects = (T[]) copied;
    }

    private void validate(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist [" + index + "]");
        }
    }
}
