package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_RATE = 1.5;
    private int size;
    private int newCapacity;
    private Object[] data;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
        newCapacity = DEFAULT_CAPACITY;
    }

    private void setData(T value, int index) {

        data[index] = value;
        size++;
    }

    private void checkIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds exception");
        }
    }

    private boolean isNeedGrow() {
        return size == newCapacity;
    }

    private void grow(){
        newCapacity = (int)((this.data.length) * GROWTH_RATE);
        Object[] data = new Object[newCapacity];
        System.arraycopy(this.data, 0, data, 0, this.data.length);
        this.data = data;
    }

    private void decrease(int index) {
        Object[] data = new Object[this.data.length]; // !!! need fix
        System.arraycopy(this.data, 0, data, 0, index);
        System.arraycopy(this.data, index + 1, data, index , size -1); // ? need fix
        this.data = data;
    }

    private void increase(int index) {
        if (size + 1 == newCapacity) {
            grow();
        }
        Object[] data = new Object[this.data.length];
        System.arraycopy(this.data, 0, data, 0, index);
        System.arraycopy(this.data, index, data, index + 1 , size + 1 - index); // ?
        this.data = data;
    }

    @Override
    public void add(T value) {
        if (size == 0) {
            setData(value, size);
            return;
        }
        if (isNeedGrow()) {
            grow();
        }
        setData(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size == 0) {
            setData(value, size);
            return;
        }
        checkIndex(index);
        if (isNeedGrow()) {
            grow();
        }
        increase(index);
        setData(value, index);
    }

    @Override
    public void addAll(List<T> list) {

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
        Object element = data[index];
        decrease(index);
        size--;
        return (T)element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], element)) {
                decrease(i);
                size--;
                return element;
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
