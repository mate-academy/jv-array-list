package core.basesyntax;


import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] objects;
    private int size;

    public ArrayList() {
        objects = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (size >= objects.length) {
            grow();
        }
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
        rangeCheckForAdd(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        T value = objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Element not found");
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

    private void grow() {
        if (objects.length == size) {
            int newCapacity = size + (size >> 1);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(objects, 0, newArray, 0, objects.length);
            objects = newArray;
        }
    }

    private int indexOf(T value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (objects[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(objects[i], value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void rangeCheckForAdd(int index) {
       if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't fount value");
        }
    }
}
