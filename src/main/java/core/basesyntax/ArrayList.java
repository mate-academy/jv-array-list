package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static int capacity = 10;
    private Object[] array = new Object[capacity];
    private int size = 0;
    @Override
    public void add(T value) {
        if (size == capacity) {
            increaseCapacity();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == array.length) {
            increaseCapacity();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= capacity) {
            increaseCapacity();
        }
        int necessaryCapacity = size + list.size();

        for (int i = size, j = 0; i < necessaryCapacity; i++) {
            T element = list.get(j);
            array[i] = element;
            j++;
        }
        size = necessaryCapacity;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Error");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size){
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Error");
        }
    }

    private void increaseCapacity() {
        capacity = capacity + (capacity >> 1);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("error");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T oldValue = (T) array[index];
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return oldValue;
        } else {
            throw new ArrayListIndexOutOfBoundsException("error");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                T oldValue = (T) array[i];
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                size--;
                return (T) oldValue;
            }
        }
        throw new NoSuchElementException("Not found element");
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
