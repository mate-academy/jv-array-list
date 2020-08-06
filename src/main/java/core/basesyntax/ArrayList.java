package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private T[] arrayElements;
    private int size;

    public ArrayList() {
        arrayElements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public T[] resize() {
        T[] oldArrayElements = arrayElements;
        arrayElements = (T[]) new Object[arrayElements.length + (arrayElements.length >> 1)];
        System.arraycopy(oldArrayElements, 0, arrayElements, 0, oldArrayElements.length);
        return arrayElements;
    }

    @Override
    public void add(T value) {
        if (size > arrayElements.length - 1) {
            resize();
        }
        arrayElements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("The index is not correct");
        }
        if (size > arrayElements.length) {
            resize();
        }
        System.arraycopy(arrayElements, index, arrayElements, index + 1, size - index);
        arrayElements[index] = value;
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
        indexChecking(index);
        return arrayElements[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecking(index);
        arrayElements[index] = value;

    }

    @Override
    public T remove(int index) {
        indexChecking(index);
        T temp = arrayElements[index];
        System.arraycopy(arrayElements, index + 1, arrayElements, index, size - index - 1);
        size--;
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == arrayElements[i] || t != null && t.equals(arrayElements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such object found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean indexChecking(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("The index is not correct");
        }
        return true;
    }
}
