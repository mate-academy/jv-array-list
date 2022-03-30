package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private int currentSize;
    private int maxSize;
    private T[] objects;

    public ArrayList() {
        this.objects = (T[]) new Object[INITIAL_SIZE];
        this.currentSize = 0;
        this.maxSize = INITIAL_SIZE;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= currentSize && index != 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size() + 1) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        }
    }

    private void grow() {
        if (currentSize >= maxSize) {
            objects = Arrays.copyOf(objects, (int) (maxSize * 1.5));
            maxSize = (int) (maxSize * 1.5);
        }
    }

    @Override
    public void add(T value) {
        grow();
        objects[currentSize++] = value;

    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        grow();
        for (int i = size(); i > index; i--) {
            objects[i] = objects[i - 1];
        }
        objects[index] = value;
        currentSize++;

    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() >= maxSize) {
            maxSize = list.size();
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removed = objects[index];
        for (int i = index; i < size() - 1; i++) {
            objects[i] = objects[i + 1];
        }
        objects[size() - 1] = null;
        currentSize--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (objects[i] != null && objects[i].equals(element) || objects[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Doest element in list");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
