package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dataElements;

    public ArrayList(int currentCapacity) {
        if (currentCapacity > 0) {
            dataElements = new Object[currentCapacity];
        } else {
            throw new IllegalArgumentException("Wrong initial capacity: " + currentCapacity);
        }
    }

    public ArrayList() {
        dataElements = new Object[DEFAULT_CAPACITY];
    }

    private void setCapacity() {
        if (size >= dataElements.length) {
            dataElements = Arrays.copyOf(dataElements, (int) (dataElements.length * 3 / 2));
        }
    }

    @Override
    public void add(T value) {
        setCapacity();
        dataElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        setCapacity();
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(dataElements, index, dataElements, index + 1, size - index);
        dataElements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) dataElements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        dataElements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = (T) dataElements[index];
        System.arraycopy(dataElements, index + 1, dataElements, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(dataElements[i])) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
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
