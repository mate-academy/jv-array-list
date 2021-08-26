package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int GROW_SIZE_INDEX = 1;
    private static final int DEFAULT_SIZE = 10;
    private transient Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAddFunction(index);
        if (size == elementData.length) {
            grow();
        }
        Object[] elementData = this.elementData;
        System.arraycopy(elementData, index, this.elementData,
                index + GROW_SIZE_INDEX, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        grow();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = (T) value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + GROW_SIZE_INDEX, elementData, index,
                elementData.length - index - GROW_SIZE_INDEX);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || (element != null && element.equals(elementData[i]))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
    }

    private void rangeCheckForAddFunction(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * 1.5);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

}
