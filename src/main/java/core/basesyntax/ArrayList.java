package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int INITIAL_LENGTH = 10;
    private T[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = (T[]) new Object[INITIAL_LENGTH];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == dataArray.length) {
            optimizeCapacity();
        }
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index");
        }
        if (size == dataArray.length) {
            optimizeCapacity();
        }
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
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
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Wrong index");
        }
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Wrong index");
        }
        dataArray[index] = value;
    }

    @Override

    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index");
        }
        T found = dataArray[index];
        System.arraycopy(dataArray,index + 1,dataArray,index,size - index);
        size--;
        return found;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((dataArray[i] == t) || (dataArray[i] != null && dataArray[i].equals(t))) {
                return remove(i);
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
        return size == 0 ? true : false;
    }

    private void optimizeCapacity() {
        T[] optimized = (T[]) new Object[size + size / 2];

        System.arraycopy(dataArray, 0, optimized, 0, size);
        dataArray = optimized;
    }
}
