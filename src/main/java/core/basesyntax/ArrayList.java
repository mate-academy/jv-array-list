package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_TIMES_VALUE = 1.5;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (data.length == this.size) {
            resize();
        }
        data[this.size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException("index <" + index
                    + "> out of ArrayList current size bounds <" + this.size + ">");
        }
        if (data.length == this.size) {
            resize();
        }
        for (int i = this.size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        this.data[index] = value;
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
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedData = (T) data[index];
        System.arraycopy(this.data, index + 1, this.data, index, this.size - 1 - index);
        this.size--;
        return removedData;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            T currentElement = (T) this.data[i];
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

    private void resize() {
        int newCapacity = (int)(data.length * GROW_TIMES_VALUE);
        Object[] resizedData = new Object[newCapacity];
        System.arraycopy(this.data, 0, resizedData, 0, this.data.length);
        this.data = resizedData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayListIndexOutOfBoundsException("index <" + index
                    + "> out of ArrayList current size bounds <" + this.size + ">");
        }
    }
}
