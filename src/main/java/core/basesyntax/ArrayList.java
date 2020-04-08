package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        size = 0;
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Please enter positive value");
        } else if (initialCapacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            elementData = new Object[initialCapacity];
        }
    }

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Put correct index");
        }
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        //add elem on the last place - ne perezatiraet
        if (index == size) {
            add(value);
        }
        rangeCheck(index);
        resize();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T value = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        size--;
        return value;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], t)) {
                remove(i);
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {

        if (size >= MAX_ARRAY_SIZE) {
            throw new OutOfMemoryError("out of memory");
        }
        if (size == elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1 + 1);;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
}
