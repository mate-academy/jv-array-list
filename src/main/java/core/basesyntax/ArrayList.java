package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private final int DEFAULT_SIZE = 10;
    private int size;
    private Object[] objects;

    private void checkingSizeOfList() {
        if (size == objects.length) {
            objects = Arrays.copyOf(objects,size + size / 2);
        }
    }

    private boolean validationIndex(int index) {
        return index >= size || index < 0;
    }

    private int findByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(objects[i], value)) {
                return i;
            }
        }
        return -1;
    }

    private void removeObject(int index) {
        objects[index] = null;
        if (index != size - 1) {
            System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        }
        size--;
    }

    public ArrayList() {
        objects = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkingSizeOfList();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. " +
                    "Index out of bounds.");
        }
        checkingSizeOfList();
        if (index != size) {
            System.arraycopy(objects, index, objects, index + 1, size - index);
        }
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
        if (validationIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. " +
                    "Index out of bounds.");
        }
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (validationIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. " +
                    "Index out of bounds.");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (validationIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. " +
                    "Index out of bounds.");
        }
        Object object = objects[index];
        removeObject(index);
        return (T) object;
    }

    @Override
    public T remove(T value) {
        int index = findByValue(value);
        if (index == -1) {
            throw new NoSuchElementException("This value is missing in list");
        }
        removeObject(index);
        return value;
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
