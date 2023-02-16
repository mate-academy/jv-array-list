package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INCRICE = 1.5;
    private int size;
    private T[] elementT;

    public ArrayList() {
        elementT = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void resize() {
        int capacity = (int) (elementT.length * CAPACITY_INCRICE);
        T[] tempArray = (T[]) new Object[capacity];
        System.arraycopy(elementT, 0, tempArray, 0, size);
        elementT = tempArray;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on position " + index);
        }
        if (size == elementT.length) {
            resize();
        }
        System.arraycopy(elementT, index, elementT, index + 1,size - index);
        elementT[index] = value;
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
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("No such index " + index);
        } else {
            return elementT[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Can't set element on position " + index);
        } else {
            elementT[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No such index " + index);
        }
        final T temp = elementT[index];
        System.arraycopy(elementT, index + 1, elementT, index, size - index - 1);
        elementT[--size] = null;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementT[i] == element)
                    || ((elementT[i] == element)
                    || (elementT[i] != null && elementT[i].equals(element)))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't found element by value");
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
