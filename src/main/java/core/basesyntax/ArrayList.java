package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] dataElements;
    private int currentLength;
    private int capacity;

    public ArrayList() {
        dataElements = (T[]) new Object[DEFAULT_SIZE];
        currentLength = 0;
        capacity = DEFAULT_SIZE;
    }

    public ArrayList(int size) {
        dataElements = (T[]) new Object[size];
        currentLength = 0;
        capacity = size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= currentLength) {
            throw new ArrayIndexOutOfBoundsException("wrong input idex");
        }
    }

    private void extendSpace() {
        if (size() >= capacity) {
            Object[] tempSet = Arrays.copyOf(dataElements, (capacity * 3) / 2);
            dataElements = tempSet;
            capacity = (capacity * 3) / 2;
        }
    }

    @Override
    public void add(T value) {
        extendSpace();
        dataElements[currentLength] = value;
        currentLength++;
    }

    @Override
    public void add(T value, int index) {
        extendSpace();
        checkIndex(index);

        System.arraycopy(dataElements, index, dataElements, index + 1, currentLength - index);
        dataElements[index] = value;
        currentLength++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) dataElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        dataElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedObject = (T) dataElements[index];
        System.arraycopy(dataElements, index + 1, dataElements, index, currentLength - index - 1);
        currentLength--;
        return removedObject;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size(); i++) {
            if (t == null && dataElements[i] == null) {
                return remove(i);
            }
            if (dataElements[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return currentLength;
    }

    @Override
    public boolean isEmpty() {
        return currentLength == 0;
    }
}
