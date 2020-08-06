package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            array = resize();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAddElement(index);
        array = resize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        checkIndex(index);
        return array[index];
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = array[index];
        int numMoved = size - index - 1;
        System.arraycopy(array, index + 1, array, index, numMoved);
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t || array[i] != null && array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element doesn't exist");
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] resize() {
        int newCapacity = array.length * 3 / 2 + 1;
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Element with such index doesn't exist!");
        }
    }

    private void checkIndexToAddElement(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Element with such index doesn't exist!");
        }
    }
}
