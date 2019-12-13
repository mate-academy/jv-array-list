package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] elementData;
    private int size = 0;

    public ArrayList(int capacity) {
        if (capacity >= 0) {
            this.elementData = this.elementData = (T[]) new Object[capacity];
        }
        if (capacity != 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        T[] temp = (T[]) new Object[(elementData.length * 3 / 2) + 1];
        temp = Arrays.copyOf(elementData, temp.length);
        elementData = temp;
    }

    private void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Illegal Index: " + index);
        }
    }

    @Override
    public void add(T value) {
        if (!(elementData.length > size)) {
            this.grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        this.validIndex(index);
        if (!(elementData.length > size)) {
            this.grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (!((elementData.length - size) > (list.size() * 2))) {
            this.grow();
        }
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        this.validIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        this.validIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        this.validIndex(index);
        T removed = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size-- - index);
        elementData[size] = null;
        return removed;
    }

    @Override
    public T remove(T t) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == t || elementData[i].equals(t)) {
                    return this.remove(i);
                }
            }
            throw new NoSuchElementException("Element: " + t);
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
