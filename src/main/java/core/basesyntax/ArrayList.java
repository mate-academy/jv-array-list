package core.basesyntax;

import java.util.Arrays;

/*
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int size = 0;
    private Object[] elementsData;
    java.util.ArrayList arrayList = new java.util.ArrayList();

    public ArrayList() {
        elementsData = new Object[CAPACITY];
    }

    public ArrayList(int userCapacity) {
        elementsData = new Object[userCapacity];
    }

    public void outOfCapacity(int minCapacity) {
        elementsData = Arrays.copyOf(elementsData, minCapacity + elementsData.length);
    }

    @Override
    public void add(T value) {
        outOfCapacity(size + 1);
        size++;
        elementsData[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size - 1) {
            this.add(value);
        } else {
            outOfCapacity(size + 1);
            size++;
            System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
            elementsData[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        outOfCapacity(size + 1);
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
        T elem = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        elementsData[size--] = null;
        return elem;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementsData[i].equals(t)) {
                T elem = (T) elementsData[i];
                remove(i);
                return elem;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Index out of range");
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