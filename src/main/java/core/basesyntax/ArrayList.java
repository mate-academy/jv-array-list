package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static int MAX_ARRAY_SIZE = 4;
    private int size = 0;
    private Object[] objects;

    public ArrayList() {
        objects = new Object[MAX_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size < MAX_ARRAY_SIZE) {
            objects[size] = value;
            size++;
            return;
        }
        arrayGrow();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add by index "
                    + index + " is out of size " + size);
        }
        if (index == size) {
            add(value);
            return;
        }
        arrayGrow();
        System.arraycopy(objects, index == 0 ? 0 : index - 1,
                objects, index == 0 ? 1 : index, size);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        objects = Arrays.copyOf(objects, size + list.size() + MAX_ARRAY_SIZE / 2);
        for (int i = 0, j = size(); i < list.size(); i++, j++) {
            objects[j] = list.get(i);
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't get by index "
                    + index + " is out of or equals size " + size);
        }
        return (T)objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of or equals size " + size);
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is out of or equals size " + size);
        }
        T removed = (T)objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);

        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        T removed = null;
        for (int i = 0; i < size; i++) {
            if (objects[i] != null && objects[i].equals(element)
                    || objects[i] == null && element == null) {
                removed = element;
                System.arraycopy(objects, i + 1, objects, i, size - i - 1);

                --size;
                return removed;
            }
        }
        if (removed == null) {
            throw new NoSuchElementException("Element " + element + " is out of range");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void arrayGrow() {
        if (size >= MAX_ARRAY_SIZE) {
            objects = Arrays.copyOf(objects, size + MAX_ARRAY_SIZE / 2);
        }
    }
}
