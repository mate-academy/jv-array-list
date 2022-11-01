package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public void resize() {
        if (elementData.length == size) {
            Object[] newArray = new Object[(int) (elementData.length * GROW_COEFFICIENT)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    public void checkArray(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No element with index:" + index);
        }
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkArray(index);
        }
        resize();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        checkArray(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkArray(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkArray(index);
        T value = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementData[i])) {
                T value = (T) elementData[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException("Element not found:" + element);
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
