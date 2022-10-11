package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int actualCapacity = DEFAULT_CAPACITY;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkBoundsIndex(index);
        Object[] elementData = this.elementData;
        grow();
        System.arraycopy(elementData, index, elementData, index + 1,size - index);
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
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue = null;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null
                    && element.equals(elementData[i])) {
                oldValue = (T) elementData[i];
                fastRemove(i);
                return oldValue;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity >= actualCapacity) {
            int newCapacity = oldCapacity + (int) (oldCapacity * 0.5);
            actualCapacity += newCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void checkBoundsIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void fastRemove(int index) {
        size--;
        Object[] tmpArray = new Object[size];
        System.arraycopy(elementData, 0, tmpArray, 0, index);
        System.arraycopy(elementData, index + 1, tmpArray, index, size - index);
        elementData = tmpArray;
    }
}
