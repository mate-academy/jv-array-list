package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private int currentCapacity = INITIAL_CAPACITY;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == currentCapacity) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        T[] temporaryArray = (T[]) new Object[size];
        System.arraycopy(elementData, 0, temporaryArray, 0, size);
        if (size == currentCapacity) {
            resize();
        }
        isIndexValid(index, "ADD");
        elementData[index] = value;
        System.arraycopy(temporaryArray,
                index,
                elementData,
                index + 1,
                size - index);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] temporaryArray = (T[]) new Object[list.size()];
        for (int i = 0; i < temporaryArray.length; i++) {
            temporaryArray[i] = list.get(i);
        }
        System.arraycopy(temporaryArray,
                0,
                elementData,
                size,
                list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        isIndexValid(index, "noADD");
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index, "noADD");
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index, "noADD");
        T oldRecord = elementData[index];
        System.arraycopy(elementData,
                index + 1,
                elementData,
                index,
                size - index - 1);
        size--;
        return oldRecord;
    }

    @Override
    public T remove(T element) {
        int temporaryIndex = -1;
        T oldRecord = (T) new Object();
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null
                    && elementData[i].equals(element))) {
                temporaryIndex = i;
                oldRecord = elementData[i];
                break;
            }
        }
        if (temporaryIndex != -1) {
            System.arraycopy(elementData,
                    temporaryIndex + 1,
                    elementData,
                    temporaryIndex,
                    size - temporaryIndex - 1);
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

    private void resize() {
        currentCapacity *= GROW_COEFFICIENT;
        T[] temporaryArray = (T[]) new Object[size];
        System.arraycopy(elementData, 0, temporaryArray, 0, size);
        elementData = (T[]) new Object[currentCapacity];
        System.arraycopy(temporaryArray, 0, elementData, 0, size);
    }

    private void isIndexValid(int index, String operation) {
        if ((operation.equals("ADD") && (index > size || index < 0))
                || (operation.equals("noADD") && (index < 0 || index >= size))) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
