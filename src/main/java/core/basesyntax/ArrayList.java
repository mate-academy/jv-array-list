package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] storage;
    private int position = 0;

    public ArrayList() {
        storage = new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (position > 0 && position == storage.length) {
            resize();
            storage[position] = value;
            position++;
        } else {
            storage[position] = value;
            position++;
        }
    }

    @Override
    public void add(T value, int index) {
        position++;
        for (int i = position - 1; i > index; i--) {
            set((T) storage[i - 1], i);
        }
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
        if (index >= position || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= position || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > position || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T temp = get(index);
        for (int i = index; i < position; i++) {
            storage[i] = storage[i + 1];
        }
        position--;
        return temp;
    }

    @Override
    public T remove(T t) {
        T temp;
        for (int i = 0; i < position; i++) {
            if (storage[i] == null || storage[i].equals(t)) {
                temp = (T) storage[i];
                remove(i);
                return temp;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public boolean isEmpty() {
        if (storage[0] == null) {
            return true;
        }
        return false;
    }

    private void resize() {
        Object[] temp = new Object[storage.length * 2];
        for (int i = 0; i < storage.length; i++) {
            temp[i] = storage[i];
        }
        storage = temp;
    }
}
