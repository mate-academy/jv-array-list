package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = (T[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal argument " + initialCapacity);
        }
    }

    @Override
    public void add(T value) {
        isFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        isFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        isFull();
        if (size + list.size() > elementData.length) {
            elementData = grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        indexIsOut(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexIsOut(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElement = (T) elementData[index];
        int deletedIndex = index;
        System.arraycopy(elementData, deletedIndex + 1,
                elementData, deletedIndex, size - deletedIndex - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        T removedElement = t;
        for (int i = 0; i < size; i++) {
            if (t == elementData[i] || t != null
                    && t.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0 ? false : true;
    }

    public void isFull() {
        if (size == elementData.length) {
            grow(elementData.length);
        }
        return;
    }

    public void indexIsOut(int index) {
        if (index >= 0 && index < size) {
            return;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is wrong!");
        }
    }

    private Object[] grow(int currentCapacity) {
        int oldCapacity = currentCapacity;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elementData = Arrays.copyOf(elementData, newCapacity);
        return elementData;
    }
}
