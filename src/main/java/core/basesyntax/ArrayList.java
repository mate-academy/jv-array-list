package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;
    private int size = 0;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
    }

    private void raiseIfFull() {
        if (list.length == size) {
            list = Arrays.copyOf(list, (list.length * 3) / 2);
        }
    }

    private void checkAvailable(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index + " index out of bounds");
        }
    }

    @Override
    public void add(T value) {
        raiseIfFull();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        raiseIfFull();
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        checkAvailable(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkAvailable(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkAvailable(index);
        Object result = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return (T) result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(t) || list[i] == t) {
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
        return size == 0;
    }
}
