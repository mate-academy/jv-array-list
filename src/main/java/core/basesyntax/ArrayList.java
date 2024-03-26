package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE = 10;
    public static final double ADD_SIE = 1.5;
    private T[] mainData;
    private int size;

    public ArrayList() {
        mainData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        arrayCapacityControl();
        mainData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index);
        }
        arrayCapacityControl();
        System.arraycopy(mainData, index, mainData,index + 1,size - index);
        mainData[index] = value;
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
        return mainData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        mainData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = mainData[index];
        System.arraycopy(mainData, index + 1, mainData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && mainData[i] == null)
                    || (element != null && element.equals(mainData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index
                    + ", size: " + size);
        }
    }

    private void arrayCapacityControl() {
        if (mainData.length == size) {
            T[] newMainData = (T[]) new Object[(int) (mainData.length * ADD_SIE)];
            System.arraycopy(mainData, 0, newMainData, 0, mainData.length);
            mainData = newMainData;
        }
    }
}
