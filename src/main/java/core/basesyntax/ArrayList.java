package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_RANGE = 10;
    private Object[] objects;
    private int size = 0;

    public ArrayList() {
        objects = new Object[MAX_RANGE];
    }

    @Override
    public void add(T value) {
        checkLength(size);
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if ((size - index) >= 0) {
            checkLength(size);
            System.arraycopy(objects, index, objects, index + 1, size - index);
            objects[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " lager then size: " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        checkSize(index);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        checkSize(index);
        remove(index);
        add(value, index);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        checkSize(index);
        Object removedObject = objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return (T) removedObject;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        Object removedObject;
        for (int i = 0; i < size; i++) {
            if (element == objects[i]
                    || objects[i] != null
                    && objects[i].equals(element)) {
                removedObject = objects[i];
                remove(i);
                return (T) removedObject;
            }
        }
        throw new NoSuchElementException("Cant find " + element);
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
        Object[] newObject = new Object[(objects.length * 3 / 2)];
        System.arraycopy(objects, 0, newObject, 0, objects.length);
        objects = newObject;
    }

    public void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " less then 0");
        }
    }

    public void checkSize(int index) {
        if (size <= index) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " larger then size: " + size);
        }
    }

    public void checkLength(int size) {
        if (size >= objects.length) {
            grow();
        }
    }
}
