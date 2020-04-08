package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 10;
    private T[] elementData;
    private int lastIndex;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
        lastIndex = 0;
    }

    public ArrayList(int capacity) {
        elementData = (T[]) new Object[capacity];
        lastIndex = 0;
    }

    @Override
    public void add(T value) {
        if (lastIndex >= elementData.length) {
            ensureCapacity();
        }
        elementData[lastIndex++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < lastIndex || index >= 0) {
            System.arraycopy(elementData, index, elementData, index + 1, lastIndex - index);
            elementData[index] = value;
            lastIndex++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T item = null;
        if (checkIndex(index)) {
            item = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, lastIndex - index - 1);
            elementData[--lastIndex] = null;
        }
        return item;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < lastIndex; i++) {
            if (elementData[i] == t || elementData[i] != null && elementData[i].equals(t)) {
                remove(i);
                return t;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    private boolean checkIndex(int index) {
        if (index >= lastIndex || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

    private T[] ensureCapacity() {
        T[] array = elementData;
        elementData = (T[]) new Object[(elementData.length * 3) / 2 + 1];
        System.arraycopy(array, 0, elementData, 0, lastIndex);
        return array;
    }
}
