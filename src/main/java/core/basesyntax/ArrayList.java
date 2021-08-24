package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEF_CAPACITY = 10;
    private int sizeArray;
    private T[] elements;
    private int capasity;

    public ArrayList(int sizeArray, T[] elements) {
        this.sizeArray = sizeArray;
        this.elements = elements;
    }

    public ArrayList() {
        capasity = DEF_CAPACITY;
        elements = (T[]) new Object[capasity];
    }

    private void grow() {
        capasity *= 1.5;
        T[] tempArray = (T[]) new Object[capasity];
        System.arraycopy(elements, 0, tempArray, 0, sizeArray);
        elements = tempArray;
    }

    private void checkItem(int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public void add(T value) {
        if (sizeArray == elements.length) {
            grow();
        }
        add(value, sizeArray);
    }

    @Override
    public void add(T value, int index) {
        checkItem(index);
        System.arraycopy(elements, index, elements, index + 1, sizeArray - index);
        elements[index] = value;
        sizeArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkItem(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkItem(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkItem(index);
        T removeObject = elements[index];
        sizeArray--;
        System.arraycopy(elements, index + 1, elements, index, sizeArray - index);
        return removeObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < sizeArray; i++) {
            if (element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element doesn't exist");
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeArray == 0;
    }
}
