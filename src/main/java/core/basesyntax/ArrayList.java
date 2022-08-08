package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects = new Object[0];
    private int size;
    private int capacity;

    public ArrayList() {
        this.capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size >= capacity) {
            capacity = grow();
        }
        Object[] temp = new Object[size];
        System.arraycopy(objects, 0, temp, 0, size);
        objects = new Object[capacity];
        System.arraycopy(temp, 0, objects, 0, size);
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == capacity) {
            capacity = grow();
        }
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist: " + index);
        }
        if (index == size) {
            add(value);
            return;
        }
        Object[] res = new Object[size + 1];
        if (index == 0) {
            res[0] = value;
        } else {
            System.arraycopy(objects, 0, res, index - 1, index);
            res[index] = value;
        }
        System.arraycopy(objects, index, res, index + 1, size - index);
        objects = new Object[capacity];
        System.arraycopy(res, 0, objects, 0, res.length);
        size++;
    }

    private int grow() {
        capacity += capacity >> 1;
        return capacity;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
            add((T) arr[i]);
        }
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "No element with such index in list: " + index);
        }
        return (T) (objects[index]);
    }

    @Override
    public void set(T value, int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "No element with such index in list: " + index);
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "No element with such index in list: " + index);
        }
        T deletedObject = (T) objects[index];
        if (index == size - 1) {
            objects[index] = null;
        } else {
            if (size - (index + 1) >= 0) {
                System.arraycopy(objects, index + 1, objects,
                        index + 1 - 1, size - (index + 1));
            }
            objects[size - 1] = null;
        }
        size--;
        return deletedObject;
    }

    @Override
    public T remove(T element) {
        boolean elementFound = false;
        T removedObject = null;

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == element || (element != null && element.equals(objects[i]))) {
                removedObject = remove(i);
                elementFound = true;
                break;
            }
        }
        if (elementFound) {
            return removedObject;
        } else {
            throw new NoSuchElementException("No such element in list: "
                    + element);
        }
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
