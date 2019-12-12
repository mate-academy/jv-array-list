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
        refrashList();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexAcceptableLimits(index);
        refrashList();
        for (int i = size - 1; i >= size - (size - index); i--) {
            list[i + 1] = list[i];
        }
        list[index] = value;
        size++;
    }


    @Override
    public void addAll(List<T> collection) {
            for (int i = 0; i < collection.size(); i++) {
                refrashList();
                add(collection.get(i));
            }
    }

    @Override
    public T get(int index) {
        isIndexAcceptableLimits(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexAcceptableLimits(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexAcceptableLimits(index);
        T item = (T) list[index];
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
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

    private void refrashList() {
        if (!isCapacityLengthEnsure()) {
            list = Arrays.copyOf(list, (list.length * 3) / 2 + 1);
        }
    }

    private boolean isIndexAcceptableLimits(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index -" + index + " outside the array ");
        }
        return true;
    }

    private boolean isCapacityLengthEnsure() {
        return size < list.length;
    }
}

