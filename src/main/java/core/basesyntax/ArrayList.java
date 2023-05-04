package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double STEP_UPHILL = 1.5d;
    private T[] dataCollection;
    private int size;

    public ArrayList() {
        dataCollection = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == dataCollection.length) {
            dataCollection = grow(size + 1);
        }
        dataCollection[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (size == dataCollection.length) {
                dataCollection = grow(size + 1);
            }

            System.arraycopy(dataCollection, index, dataCollection, index + 1, size - index);
            dataCollection[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newCapacity = list.size() + size;
        int index = size;
        dataCollection = grow(newCapacity);
        for (int i = 0; i < list.size(); i++) {
            dataCollection[index] = list.get(i);
            index++;
        }
        this.size = newCapacity;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return dataCollection[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataCollection[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T data = dataCollection[index];
        removeExtraIndex(dataCollection, index);
        size--;
        return data;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            Object object = dataCollection[i];
            if ((element == null && object == null)
                    || (object != null && object.equals(element))) {
                removeExtraIndex(dataCollection, i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("The element " + element + " is absent");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow(int needSize) {
        int newCapacity = dataCollection.length;
        if (newCapacity == 0) {
            return (T[]) new Object[Math.max(DEFAULT_CAPACITY, needSize)];
        }

        do {
            newCapacity = (int) (newCapacity * STEP_UPHILL);
        } while (newCapacity < needSize);

        T[] renewed = (T[]) new Object[newCapacity];
        System.arraycopy(dataCollection, 0, renewed, 0, size);
        return renewed;
    }

    private void removeExtraIndex(T[] array, int index) {
        for (int i = index + 1; i < size; i++) {
            array[index] = array[i];
            index++;
        }
        array[size - 1] = null;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array bounds: " + index);
        }

    }
}
