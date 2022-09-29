package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_TIMES_VALUE = 1.5;
    private Object[] data = new Object[DEFAULT_CAPACITY];
    private int size = 0;

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
        size++;
        checkIndex(index);
        if (data.length == this.size) {
            resize();
        }
        for (int i = this.size - 2; i >= index; i--) {
            data[i + 1] = data[i];
        }
        this.data[index] = value;
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
        this.size--;
        T removedData = (T) data[index];
        for (int i = index; i < this.size; i++) {
            this.data[i] = this.data[i + 1];
        }
        this.data[size] = null;
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

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayListIndexOutOfBoundsException("index <" + index
                    + "> out of ArrayList current size bounds <" + this.size + ">");
        }
    }

    private void resize() {
        int newCapacity = (int)(data.length * GROW_TIMES_VALUE);
        Object[] resizedData = new Object[newCapacity];
        System.arraycopy(this.data, 0, resizedData, 0, this.data.length);
        this.data = resizedData;
    }
}
