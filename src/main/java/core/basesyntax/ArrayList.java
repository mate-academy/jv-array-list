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
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            ensureCapacity();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        isIndexExist(index);
        if (elementData.length <= size + 1) {
            ensureCapacity();
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
        isIndexExist(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexExist(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexExist(index);
        T deletedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size--] = null;
        return deletedValue;
    }

    @Override
    public T remove(T t) {
        for (int index = 0; index < size; index++) {
            if (t == elementData[index] || t != null && t.equals(elementData[index])) {
                return remove(index);
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

    private void ensureCapacity() {
        int newCapacity = size >> 1;
        elementData = Arrays.copyOf(elementData, size + newCapacity);
    }

    private void isIndexExist(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
