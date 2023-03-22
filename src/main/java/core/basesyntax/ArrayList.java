package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void resizeIdNeeded() {
        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + (elements.length / 2)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayListIndexOutOfBoundsException("index <" + index
                    + "> out of ArrayList current size bounds <" + this.size + ">");
        }
    }

    @Override
    public void add(T value) {
        resizeIdNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException("index <" + index
                    + "> out of ArrayList current size bounds <" + this.size + ">");
        }

        if (elements.length == this.size) {
           resizeIdNeeded();
        }

        for (int i = this.size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        this.elements[index] = value;
        this.size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(this.elements, index + 1, this.elements, index, this.size - 1 - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            T currentElement = (T) this.elements[i];
            if (currentElement == element || currentElement != null
                    && currentElement.equals(element)) {
                this.remove(i);
                return currentElement;
            }
        }
        throw new NoSuchElementException("Cannot find element <"
                + (element == null ? "null" : element.toString()) + ">");
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
