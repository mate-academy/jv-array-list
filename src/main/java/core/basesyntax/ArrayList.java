package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ONE_ELEMENT = 1;
    private int size;
    private T[] elementData;

    public ArrayList() {
        size = 0;
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            size = initialCapacity;
            elementData = (T[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = (T[]) new Object[0];
        } else {
            throw new RuntimeException("Number of sheet elements is incorrect");
        }
    }

    public void checkingIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds search index");
        }
    }

    public void ensureCapacity() {
        if (elementData.length - 1 - size >= ONE_ELEMENT) {
            return;
        }
        T[] newArrayData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, newArrayData, 0, size);
        elementData = newArrayData;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
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
        checkingIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkingIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkingIndex(index);
        T removed = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == t || elementData[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
}
