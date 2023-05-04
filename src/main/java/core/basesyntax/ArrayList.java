package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double DEFAULT_GROW = 1.5;
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is invalid");
        }
        resize();
        System.arraycopy(elements,index,elements,index + 1,size - index);
        elements[index] = value;
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
        check(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        check(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        check(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements,index + 1,elements,index,size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || element != null && element.equals(elements[i])) {
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

    private void resize() {
        if (elements.length == size) {
            Object[] newArray = new Object[(int) (elements.length * DEFAULT_GROW)];
            System.arraycopy(elements,0, newArray,0,size);
            elements = newArray;
        }
    }

    private void check(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is invalid");
        }
    }
}
