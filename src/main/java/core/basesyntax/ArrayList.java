package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T[] grow() {
        return elementData = Arrays.copyOf(elementData, newCapacity());
    }

    private int newCapacity() {
        return elementData.length + (elementData.length >> 1);
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + " size: " + size);
        }
        T[] elementData;
        if (size == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] currentList = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            currentList[i] = list.get(i);
        }
        if ((elementData.length - size) < currentList.length) {
            elementData = grow();
        }
        System.arraycopy(currentList, 0, elementData, size, currentList.length);
        size = size + currentList.length;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bound for length " + size);
        }

        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index should not be "
                    + "bigger than size. Index: "
                    + index + ", size: " + size);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no element at this index");
        }
        T oldValue = elementData[index];
        System.arraycopy(elementData,index + 1, elementData, index,
                elementData.length - (index + 1));
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue = null;
        int check = 0;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                oldValue = elementData[i];
                int index = i;
                System.arraycopy(elementData,index + 1, elementData,
                        index, elementData.length - (index + 1));
                size--;
                check++;
                return oldValue;
            }
        }
        if (check == 0) {
            throw new NoSuchElementException("There is no such element");
        }
        return oldValue;
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
