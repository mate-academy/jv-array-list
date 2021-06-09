package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;
    private int maxSize;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        maxSize = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == maxSize) {
            increaseSize();
        }
        ++size;
        array[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index - 1 > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        if (size + 1 == maxSize) {
            increaseSize();
        }
        System.arraycopy(array, index, array, index + 1, size++ - index);
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > DEFAULT_CAPACITY) {
            throw new RuntimeException("IndexOut");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index + 1 > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        T value = array[index];
        if (index == size - 1) {
            array[index] = null;
            --size;
            return value;
        }
        System.arraycopy(array,index + 1,array,index,size - index);
        --size;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseSize() {
        T[] tempArray = array;
        array = (T[]) new Object[tempArray.length + tempArray.length / 2];
        maxSize = array.length;
        System.arraycopy(tempArray,0,array,0,tempArray.length);
    }
}
