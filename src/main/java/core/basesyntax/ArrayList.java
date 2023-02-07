package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        this.objects = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (objects.length == size) {
            grow();
        }
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is bigger than"
                    + " array size or less than null");
        }

        if (size == objects.length) {
            grow();
        }
        System.arraycopy(objects, index,
                objects, index + 1,
                size - index);
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
        checkIndex(index);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        objects[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, objects[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The object " + element
                + " didn't found in collection");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void grow() {
        System.arraycopy(objects,
                0, objects = new Object[countNewCapacity()], 0, size());
    }

    private int countNewCapacity() {
        int oldCapacity = objects.length;
        return (oldCapacity + (oldCapacity >> 1));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + " out of size: " + size);
        }
    }
}
