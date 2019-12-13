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
        resizeOnOneElement(index, 1);
        list[index] = value;
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
        final T item = (T) list[index];
        resizeOnOneElement(index, -1);
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

    private void resizeOnOneElement(int index, int marker) {
        checkIndex(index);
        resizeIfFull();
        System.arraycopy(list, index + (marker < 0 ? 1 : 0), list,
                index + (marker > 0 ? 1 : 0), size - index + (1 * marker));
        size = size + marker;
        if (marker < 0) {
            list[size] = null;
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

