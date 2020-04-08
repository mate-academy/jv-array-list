package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size + 1 >= elements.length) {
            ensureCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int resultLength = size + list.size();
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
        size = resultLength;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elements[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElem = (T) elements[index];
        int numMoved = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null;
        return removedElem;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == null && t == elements[i]) || (t != null && t.equals(elements[i]))) {
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
        int newSize = (elements.length * 3) / 2 + 1;
        elements = Arrays.copyOf(elements, newSize);
    }
}
