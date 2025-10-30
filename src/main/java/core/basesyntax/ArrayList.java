package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final double ARRAY_GROW = 1.5;
    private Object[] data;
    private int sizeArray;

    public ArrayList() {
        this.data = new Object[ARRAY_SIZE];
        sizeArray = 0;
    }

    @Override
    public void add(T value) {
        increaseArray();

        data[sizeArray] = value;
        sizeArray++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);

        increaseArray();

        System.arraycopy(data, index, data, index + 1, sizeArray - index);

        data[index] = value;
        sizeArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= sizeArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= sizeArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= sizeArray) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
        final T temp = get(index);

        System.arraycopy(data, index + 1, data, index, sizeArray - index);

        sizeArray--;
        return temp;
    }

    @Override
    public T remove(T element) {
        Object temp = new Object();
        boolean find = false;

        for (int i = 0; i < sizeArray + 1; i++) {
            if ((data[i] == element) || (data[i] != null && data[i].equals(element))) {
                temp = data[i];
                remove(i);
                find = true;
                break;
            }
        }
        if (!find) {
            throw new NoSuchElementException("Element not find");
        }
        return (T) temp;
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public boolean isEmpty() {
        if (sizeArray == 0) {
            return true;
        }
        return false;
    }

    private void increaseArray() {
        if (sizeArray == data.length - 1) {
            Object[] tempArray = new Object[(int) (data.length * ARRAY_GROW)];
            System.arraycopy(data, 0, tempArray, 0, data.length);
            data = tempArray;
        }
    }

    private void checkIndex(int index) {
        if (index > sizeArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }
}
