package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (objects.length - size <= 5) {
            newSize();
        }
        objects[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        isPresentInArray(index);
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
        size++;
    }

    public void newSize() {
        int newCapacity = size >> 1;
        objects = Arrays.copyOf(objects, size + newCapacity);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return isPresentInArray(index) ? (T) objects[index] : null;
    }

    @Override
    public void set(T value, int index) {
        if (isPresentInArray(index)) {
            objects[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        isPresentInArray(index);
        T valueDelete = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        objects[size--] = null;
        return valueDelete;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (objects[i] == t || objects[i] != null && objects[i].equals(t)) {
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
        return size() == 0;
    }

    public boolean isPresentInArray(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }
}
