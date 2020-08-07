package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private int size;
    private Object[] base;

    public ArrayList() {
        base = new Object[10];
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
        checkIndex(index, size);
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
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        setCapacity();
        checkIndex(index, size - 1);
        return (T) base[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        base[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T result = (T) base[index];
        System.arraycopy(base, index + 1, base, index, base.length - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t != null && base[i].equals(t)) || (t == base[i])) {
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

    private void setCapacity() {
        if (1 + size > base.length) {
            int newCapacity = (base.length / 2) + base.length;
            Object[] tempBase = new Object[newCapacity];
            System.arraycopy(base, 0, tempBase, 0, base.length);
            base = tempBase;
        }
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
