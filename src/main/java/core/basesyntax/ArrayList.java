package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int CAPACITY = 10;
    private int dataCapacity;
    private int size;
    private Object[] base;

    public ArrayList() {
        base = new Object[0];
        size = 0;
    }

    @Override
    public void add(T value) {
        setCapacity();
        base[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (base.length <= index) {
            setCapacity();
        } else {
            System.arraycopy(base, index, base, index + 1, base.length - index - 1);
        }
        base[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        setCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        setCapacity();
        if (!(index >= 0 && index < size)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) base[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        base[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = (T) base[index];
        System.arraycopy(base, index + 1, base, index, base.length - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        boolean issuedValue = false;
        for (int i = 0; i < size; i++) {
            if ((t != null && base[i].equals(t)) || (t == base[i])) {
                remove(i);
                issuedValue = true;
                return t;
            }
        }
        if (!issuedValue) {
            throw new NoSuchElementException();
        }
        return t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void setCapacity() {
        setCapacity(1);
    }

    private void setCapacity(int space) {
        if (dataCapacity == 0) {
            base = new Object[CAPACITY];
            dataCapacity = CAPACITY;
        }
        if (space + size > dataCapacity) {
            int newCapacity = (dataCapacity / 2) + dataCapacity;
            Object[] tempBase = new Object[newCapacity];
            dataCapacity = newCapacity;
            System.arraycopy(base, 0, tempBase, 0, base.length);
            base = tempBase;
        }
    }
}
