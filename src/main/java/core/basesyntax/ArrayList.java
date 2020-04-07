package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int size = 0;
    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.elementData = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (!ensureCapacity(size + 1)) {
            ensurenseCapacity();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (!ensureCapacity(size + 1)) {
            ensurenseCapacity();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (size <= index || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T deletedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return deletedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == elementData[i] || (t != null && t.equals(elementData[i]))) {
                T deletedElement = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                elementData[--size] = null;
                return deletedElement;
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

    public void ensurenseCapacity() {
        int num = (elementData.length * 3) / 2 + 1;
        Object[] newCapacity = new Object[num];
        System.arraycopy(elementData, 0, newCapacity, 0, size);
        elementData = (T[]) newCapacity;
    }

    public boolean ensureCapacity(int needsSpase) {
        return elementData.length >= needsSpase;
    }
}
