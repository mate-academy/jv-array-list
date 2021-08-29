package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int current_capacity = INITIAL_CAPACITY;
    private T[] elementData;
    private int size;
    private static final double GROW_COEFICIENT = 1.5;


    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == current_capacity) {
            current_capacity *= GROW_COEFICIENT;
            T[] temporyArray = (T[]) new Object[size];
            System.arraycopy(elementData, 0, temporyArray, 0, size);
            elementData = (T[]) new Object[current_capacity];
            System.arraycopy(temporyArray, 0, elementData, 0, size);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        T[] temporyArray = (T[]) new Object[size];
        int temporyIndex = 0;
        System.arraycopy(elementData, 0, temporyArray, 0, size);
        if (size == current_capacity) {
            current_capacity *= GROW_COEFICIENT;
            T[] localTemporyArray = (T[]) new Object[size];
            System.arraycopy(elementData, 0, localTemporyArray, 0, size);
            elementData = (T[]) new Object[current_capacity];
            System.arraycopy(localTemporyArray, 0, elementData, 0, size);
        }
//        if (size >= index && index >= 0) {
//            temporyIndex = index;
//        } else {
//
//        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        elementData[index] = value;
        System.arraycopy(temporyArray, index, elementData, index + 1,
                size - index);
        //elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] temporyArray = (T[]) new Object[list.size()];
        for (int i = 0; i < temporyArray.length; i++) {
            temporyArray[i] = list.get(i);
        }
        System.arraycopy(temporyArray, 0, elementData, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        T oldRecord = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return oldRecord;
    }

    @Override
    public T remove(T element) {
        int temporyIndex = -1;
        T oldRecord = (T) new Object();
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || (elementData[i] != null && elementData[i].equals(element))) {
                temporyIndex = i;
                oldRecord = elementData[i];
                break;
            }
        }
        if (temporyIndex != -1) {
            System.arraycopy(elementData, temporyIndex + 1, elementData,
                    temporyIndex, size - temporyIndex - 1);
            size--;
            return oldRecord;
        }
        throw new NoSuchElementException("Element not find");
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
