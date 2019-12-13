package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayOfElements;
    private int size;

    public ArrayList() {
        arrayOfElements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int capacity) {
        arrayOfElements = new Object[capacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        arrayOfElements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayIndexOutOfBoundsException {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(arrayOfElements, index, arrayOfElements, index + 1, size - index);
        arrayOfElements[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        checkIndex(index);
        return (T) arrayOfElements[index];
    }

    @Override
    public void set(T value, int index) throws ArrayIndexOutOfBoundsException {
        checkIndex(index);
        arrayOfElements[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayIndexOutOfBoundsException {
        checkIndex(index);
        T elementToDelete = (T) arrayOfElements[index];
        System.arraycopy(arrayOfElements, index + 1, arrayOfElements, index, size - index);
        arrayOfElements[size--] = null;
        return elementToDelete;
    }

    @Override
    public T remove(T t) throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        for (int i = 0; i < size; i++) {
            if (arrayOfElements[i] == t || arrayOfElements[i].equals(t)) {
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
        if (size == arrayOfElements.length) {
            Object[] newArrayOfElements = new Object[size + (size >> 1)];
            System.arraycopy(arrayOfElements, 0, newArrayOfElements, 0, size);
            arrayOfElements = newArrayOfElements;
        }
    }

    private void checkIndex(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is less than 0");
        }
    }
}


