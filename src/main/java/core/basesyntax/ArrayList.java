package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int customSize) {
        objects = new Object[customSize];
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
        if (isPresentInArray(index)) {
            for (int i = size + 1; i > index; i--) {
                objects[i] = objects[i - 1];
            }
            objects[index] = value;
            size++;
        }
    }

    public void newSize() {
        int newCapacity = (int)(objects.length * 3 * 0.5) + 1;
        T[] old = (T[]) objects;
        objects = new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            objects[i] = old[i];
        }
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
        T obj = (T) objects[index];
        if (isPresentInArray(index)) {
            objects[index] = null;
            int tmp = index;
            while (tmp < size) {
                objects[tmp] = objects[tmp + 1];
                objects[tmp + 1] = null;
                tmp++;
            }
            size--;
        }
        return obj;
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
