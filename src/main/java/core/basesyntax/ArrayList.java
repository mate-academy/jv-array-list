package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final static int DEFAULT_SIZE = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public void resize() {
        int newSize;
        if (objects.length >= DEFAULT_SIZE) {
            newSize = objects.length + (objects.length / 2);
        } else {
            newSize = objects.length + 1;
        }
        Object[] newArray = new Object[newSize];
        System.arraycopy(objects, 0, newArray, 0, size);
        objects = newArray;
    }

    public void checkIndex(int index) {
        if (size < index || index < 0) {
            throw new ArrayIndexOutOfBoundsException("can't find such index");
        }
    }

    @Override
    public void add(T value) {
        try {
            resize();
            objects[size] = value;
            size++;
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element");
        }
    }

    @Override
    public void add(T value, int index) {
        try {
            checkIndex(index);
            resize();
            System.arraycopy(objects, index + 1, objects, index + 2, size - (size - index));
            objects[index] = value;
            size++;
        } catch (Exception e) {
            throw new ArrayIndexOutOfBoundsException("Can't find an element");
        }
    }

    @Override
    public void addAll(List<T> list) {
        try {
            if (!list.isEmpty()) {
                if (size + list.size() >= DEFAULT_SIZE) {
                    resize();
                }
                Object[] arr = new Object[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    arr[i] = list.get(i);
                }
                System.arraycopy(arr, 0, objects, size, arr.length);
                size += arr.length;
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't find an element");
        }
    }

    @Override
    public T get(int index) {
        try {
            checkIndex(index);
            return (T) objects[index];
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't find an element");
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            checkIndex(index);
            for (int i = 0; i < objects.length; i++) {
                if (i == index) {
                    objects[i] = value;
                }
            }
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can't find an element");
        }
    }

    @Override
    public T remove(int index) {
        try {
            checkIndex(index);
            T removed = (T) objects[index];
            System.arraycopy(objects, index + 1, objects, index, size - index - 1);
            objects[size - 1] = null;
            size--;
            return removed;
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Can 't find an element");
        }
    }

    @Override
     public T remove(T element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (element == objects[i]) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            throw new NoSuchElementException();
        }
        T removed = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        objects[size - 1] = null;
        size--;
        return removed;
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
