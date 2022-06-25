package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementsData;

    public ArrayList() {
        this.elementsData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public T[] grow(int currentCapacity) {
        int newCapacity = currentCapacity + (currentCapacity >> 1);
        return elementsData = Arrays.copyOf(elementsData, newCapacity);
    }

    @Override
    public void add(T value) {
        add(value,size);
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is bigger then ArrayList size");
        }
        if (size == elementsData.length) {
            elementsData = grow(elementsData.length);
        }
        System.arraycopy(elementsData,index,elementsData,index + 1,size - index);
        elementsData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if ((list.size() + size) >= elementsData.length){
            elementsData = grow(elementsData.length);
        }
        for (int i = 0; i < list.size(); i++) {
            elementsData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0){
            throw new ArrayListIndexOutOfBoundsException("Index is bigger then ArrayList size");
        }
        return elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0){
            throw new ArrayListIndexOutOfBoundsException("Index is bigger then ArrayList size");
        }
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0){
            throw new ArrayListIndexOutOfBoundsException("Index is bigger then ArrayList size");
        }
        T removedValue = elementsData[index];
        System.arraycopy(elementsData,index + 1, elementsData, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (elementsData[i] == null && elementsData[i] == element
                    || elementsData[i] != null && elementsData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There are no elements in ArrayList");
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
