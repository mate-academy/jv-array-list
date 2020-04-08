package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;

    private int capacity;
    private int idx;
    private Object[] elementData;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        idx = 0;
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if ((idx + 1) == capacity) {
            grow();
        }

        elementData[idx++] = value;

    }

    @Override
    public void add(T value, int index) {
        check(index);
        System.arraycopy(elementData, index, elementData, index + 1, idx - index);
        this.elementData[index] = value;
        idx++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        check(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        check(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        check(index);
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, idx - index);
        idx--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < idx; i++) {
            if (t == elementData[i] || (t != null && t.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such value!");
    }

    @Override
    public int size() {
        return idx;
    }

    @Override
    public boolean isEmpty() {
        return idx == 0;
    }

    private void check(int index) {
        if (index >= idx || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index + " out Of Bounds");
        }
    }

    private void grow() {
        T[] clone = (T[]) Arrays.copyOf(elementData, capacity);
        capacity = 3 * capacity / 2 + 1;
        elementData = Arrays.copyOf(clone, capacity);
    }
}
