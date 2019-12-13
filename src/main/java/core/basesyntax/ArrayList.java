package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] list;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        resizeIfFull();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        resizeIfFull();
        Object[] tmp = new Object[size + 1];
        System.arraycopy(list, 0, tmp, 0, size - (size - 1));
        tmp[index] = value;
        System.arraycopy(list, index, tmp, index + 1, size - index);
        list = tmp;
        size++;
    }

    @Override
    public void addAll(List<T> collection) {
        for (int i = 0; i < collection.size(); i++) {
            resizeIfFull();
            add(collection.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T item = (T) list[index];
        Object[] tmp = new Object[size - index - 1];
        System.arraycopy(list, index + 1, tmp, 0, size - index - 1);
        System.arraycopy(tmp, 0, list, index, tmp.length);
        list[size] = null;
        size--;
        return item;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(t) || list[i] == t) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfFull() {
        if (!isCapacityLengthEnsure()) {
            list = Arrays.copyOf(list, (list.length * 3) / 2 + 1);
        }
    }

    private boolean checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index -" + index + " outside the array ");
        }
        return true;
    }

    private boolean isCapacityLengthEnsure() {
        return size < list.length;
    }
}

