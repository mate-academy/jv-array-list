package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPATHITY = 10;
    private int size;
    private Object [] elements;

    public ArrayList() {
        this.elements = new Object[CAPATHITY];
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index,size + 1);
        grow();
        System.arraycopy(elements, index, elements,index + 1,size - index);

        elements[index] = value;
        size++;
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    public void grow() {
        if (elements.length == size) {
            Object [] newArray = new Object[elements.length + elements.length / 2];
            System.arraycopy(elements,0, newArray,0, size);
            elements = newArray;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index,size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,size);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index,size);
        T remuveelement = (T) elements[index];
        System.arraycopy(elements,index + 1, elements, index,size - index - 1);
        size--;
        return remuveelement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(elements[i]) || element == elements[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Not found element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new ArrayListIndexOutOfBoundsException("We are out of sithe array");
        }
    }
}
