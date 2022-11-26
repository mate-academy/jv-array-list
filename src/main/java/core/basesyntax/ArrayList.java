package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        return objects = Arrays.copyOf(objects, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = objects.length;
        return (int) (oldCapacity + (oldCapacity * 1.5));
    }

    @Override
    public void add(T value) {
        if (size < objects.length) {
            objects[size] = value;
            size++;
        } else {
            objects = grow();
            if (size < objects.length) {
                objects[size] = value;
                size++;
            }
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is bigger than"
                   + " array size or less than null");
        }
        if (size == objects.length) {
            objects = grow();
        }
        System.arraycopy(objects, index,
                objects, index + 1,
                size - index);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] inputObjects = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            inputObjects[i] = list.get(i);
        }
        if ((objects.length - size) < inputObjects.length) {
            objects = grow();
        }
        System.arraycopy(inputObjects, 0, objects, size, inputObjects.length);
        size += inputObjects.length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounce exception");
        }
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || (index > size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Index out bounds exception");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        final int newSize;
        Object oldObject;

        if (index < 0 || (index > size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("The index is bigger than "
                   + "array size or less than null");
        }

        if (objects[index] == null) {
            oldObject = null;
        } else {
            oldObject = objects[index];
        }

        if ((newSize = size - 1) > index) {
            System.arraycopy(objects, index + 1, objects, index, size - index);
        }
        objects[newSize] = null;
        size = newSize;
        return (T) oldObject;
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
}
