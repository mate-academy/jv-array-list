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

    public T[] newArrayOfElements() {
        T[] newArrayElements = arrayElements;
        arrayElements = (T[]) new Object[arrayElements.length + (arrayElements.length >> 1)];
        System.arraycopy(newArrayElements, 0, arrayElements, 0, newArrayElements.length);
        return arrayElements;
    }

    private boolean indexChecking(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("The index is not correct");
        }
        return true;
    }

    @Override
    public void add(T value) {
        if (size > arrayElements.length - 1) {
            newArrayOfElements();
        }
        arrayElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("The index is not correct");
        }
        if (size > arrayElements.length) {
            newArrayOfElements();
        }
        System.arraycopy(arrayElements, index, arrayElements, index + 1, size - index);
        arrayElements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size > arrayElements.length) {
            newArrayOfElements();
        }
        for (int i = 0; i < list.size(); i++) {
            arrayElements[size] = list.get(i);
            size++;
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
        System.arraycopy(arrayElements, index + 1, arrayElements, index, size - index);
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
}
