package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementsStorage;
    private int size;

    public ArrayList() {
        this.elementsStorage = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void grow() {
        T[] newStorage = (T[]) new Object[elementsStorage.length + (elementsStorage.length >> 1)];
        System.arraycopy(elementsStorage, 0, newStorage, 0, size);
        elementsStorage = newStorage;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void add(T value) {
        if (elementsStorage.length == size) {
            grow();
        }
        elementsStorage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementsStorage.length) {
            grow();
        }
        System.arraycopy(elementsStorage, index, elementsStorage, index + 1, size - index);
        elementsStorage[index] = value;
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
        indexCheck(index);
        return (T) elementsStorage[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementsStorage[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T result = (T) elementsStorage[index];
        System.arraycopy(elementsStorage, index + 1, elementsStorage, index, size - index);
        elementsStorage[--size] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementsStorage[i] == t
                    || elementsStorage[i] != null && elementsStorage[i].equals(t)) {
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
}
