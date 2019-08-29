package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int currentCapacity;
    private int pointer;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
        pointer = 0;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        data[pointer++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (pointer < index) {
            throw new ArrayIndexOutOfBoundsException("Maximum allowed index: " + pointer);
        }
        checkCapacity();
        System.arraycopy(data, index, data, index + 1, pointer - index);
        data[index] = value;
        pointer++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (pointer < index) {
            throw new ArrayIndexOutOfBoundsException("Maximum allowed index: " + pointer);
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (pointer < index) {
            throw new ArrayIndexOutOfBoundsException("Maximum allowed index: " + pointer);
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (pointer < index) {
            throw new ArrayIndexOutOfBoundsException("Maximum allowed index: " + pointer);
        }
        T removedElement = (T) data[index];
        System.arraycopy(data, index + 1, data, index, pointer - index - 1);
        pointer--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        int index = indexOf(t);
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Not existing value");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    private void resize() {
        int newCapacity = (currentCapacity * 3) / 2 + 1;
        data = Arrays.copyOf(data, newCapacity);
        currentCapacity = newCapacity;
    }

    private void checkCapacity() {
        if (!(pointer < currentCapacity)) {
            resize();
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < pointer; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }
}
