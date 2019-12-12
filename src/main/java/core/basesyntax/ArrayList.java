package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] storage;
    private int size = 0;

    public ArrayList() {
        storage = new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == storage.length) {
            resize();
            storage[size] = value;
            size++;
        } else {
            storage[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        size++;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        set(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T temp = get(index);
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == null || storage[i].equals(t)) {
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
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void resize() {
        Object[] temp = new Object[storage.length * 2];
        System.arraycopy(storage, 0, temp, 0, size);
        storage = temp;
    }
}
