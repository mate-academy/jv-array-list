package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_ARRAYLIST = {};
    private Object[] objectArray = {};

    private int size;

    public ArrayList() {
        objectArray = DEFAULT_ARRAYLIST;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.objectArray = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.objectArray = DEFAULT_ARRAYLIST;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cen`t add element. " +
                    "Index " + index + " is out of range 0 - " + size);
        }
        if (objectArray.length == 0) {
            objectArray = new Object[DEFAULT_CAPACITY];
        }

        if (objectArray.length < size + 1) {
            objectArray = Arrays.copyOf(objectArray, objectArray.length +
                    (objectArray.length >> 1));
        }
        System.arraycopy(objectArray, index, objectArray, index + 1,
                (objectArray.length - 1) - index);
        objectArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index > (size - 1) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cen`t get element. " +
                    "Index " + index + " is out of range 0 - " + (size - 1));
        }
        return (T) objectArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > (size - 1) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can`t set element. " +
                    "Index " + index + " is out of range 0 - " + (size - 1));
        }
        objectArray[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (index > (size - 1) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can`t remove element. " +
                    "Index " + index + " is out of range 0 - " + (size - 1));
        }
        T result = (T) objectArray[index];
        System.arraycopy(objectArray, index + 1, objectArray, index,
                (objectArray.length - 1) - index);
        size--;
        objectArray[size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(objectArray[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Cant remove element, /n"
                + element + " not found in array list.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(i).append(" ");
            if (objectArray[i] == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(objectArray[i].toString());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
