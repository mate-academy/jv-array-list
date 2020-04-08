package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int currentCapacity;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
        size = 0;
    }

    @Override
    public void add(T value) {
        if (currentCapacity == size) {
            ensureCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (currentCapacity == size) {
            ensureCapacity();
        }
        System.arraycopy(elementData, index, elementData,index + 1,size - index);
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
        if ((index < 0) || (index >= size)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if ((index < 0) || (index >= size)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removed = elementData[index];
        System.arraycopy(elementData,index + 1, elementData, index,size - index);
        size--;
        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == null && t == null) || elementData[i].equals(t)) {
                return remove(i);
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
        currentCapacity = currentCapacity * 3 / 2 + 1;
        T[] elementDataNew = (T[]) new Object[currentCapacity];
        System.arraycopy(elementData, 0,elementDataNew,0, size);
        elementData = elementDataNew;
    }
}
