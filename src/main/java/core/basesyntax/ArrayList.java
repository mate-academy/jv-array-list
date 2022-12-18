package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int MIN_LENGTH = 10;
    private static final double MULTIPLIER = 1.5;
    private Object[] defaultArray;
    private int size;

    public ArrayList() {
        defaultArray = new Object[MIN_LENGTH];
    }

    @Override
    public void add(T value) {
        grow();
        defaultArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        grow();
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            defaultArray[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        size--;
        Object item = defaultArray[index];
        if (size > index) {
            System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index);
        }
        return (T) item;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(defaultArray[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("222");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public void checkIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new ArrayListIndexOutOfBoundsException("111");
        }
    }

    public void grow() {
        if (size == defaultArray.length) {
            int newLength = (int) (defaultArray.length * MULTIPLIER);
            Object[] newArray = new Object[newLength];
            System.arraycopy(defaultArray, 0, newArray, 0, size);
            defaultArray = newArray;
        }
    }

}
