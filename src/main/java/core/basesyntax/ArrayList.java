package core.basesyntax;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int length;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        length = 0;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = (T[]) new Object[initialCapacity];
            length = 0;
        }
        throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }

    public ArrayList(Collection<? extends T> c) {
        elementData = (T[]) c.toArray();
    }

    @Override
    public void add(T value) {
        increaseArray();
        elementData[length] = value;
        length++;
    }

    @Override
    public void add(T value, int index) {
        increaseArray();
        if (!indexIsExist(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        length++;
        System.arraycopy(elementData, index, elementData, index + 1, length - index - 1);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (!indexIsExist(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (!indexIsExist(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (indexIsExist(index)) {
            T temp = elementData[index];
            deleteByIndex(index);
            return temp;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < length; i++) {
            if (t == elementData[i] || (elementData[i] != null && elementData[i].equals(t))) {
                deleteByIndex(i);
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    private void increaseArray() {
        if (length + 1 > elementData.length) {
            T[] tempArray = elementData;
            elementData = (T[]) new Object[length + (length >> 1)];
            System.arraycopy(tempArray, 0, elementData, 0, length);
        }
    }

    private void deleteByIndex(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, length - index - 1);
        length--;
    }

    private boolean indexIsExist(int index) {
        return index < length && index >= 0;
    }
}
