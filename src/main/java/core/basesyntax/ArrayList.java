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

    private void ensureCapacity() {
        int newSize = (elements.length * 3) / 2 + 1;
        elements = Arrays.copyOf(elements, newSize);
    }

    @Override
    public void add(T value, int index) {
        if (index < size) {
            if (size + 1 >= elements.length) {
                ensureCapacity();
            }
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        int resultLength = size + list.size();
        Object[] arrFromList = new Object[list.size()];
        while (elements.length < resultLength) {
            ensureCapacity();
        }
        for (int i = 0; i < arrFromList.length; i++) {
            arrFromList[i] = list.get(i);
        }
        System.arraycopy(arrFromList, 0, elements, size, list.size());
        size = resultLength;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size){
            return (T) elements[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            elements[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
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
}
