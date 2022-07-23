package core.basesyntax;


import java.util.Arrays;
import java.util.Objects;


public class ArrayList<T> implements List<T> {


    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_INCREASE_FACTOR = 1.5;
    private Object[] elementData; // should we use transient keyword?
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = buildUp();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (index == elementData.length) {
            elementData = buildUp();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private Object[] buildUp() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * SIZE_INCREASE_FACTOR);
        return new Object[newCapacity];
    }

    private void checkIndex (int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index + " Слава Україні!");
        }
    }

}
