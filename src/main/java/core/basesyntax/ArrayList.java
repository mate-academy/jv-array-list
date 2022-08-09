package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growUp();
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growUp();
        if (index == size) {
            add(value);
            return;
        }
        if (index < size && index >= 0) {
            System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
            dataArray[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException(
                "Index " + index + " is wrong for size " + size);
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > size) {
            growUp();
        }
        for (int i = 0; i < list.size(); i++) {
            dataArray[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        validationForIndex(index);
        return (T)dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        validationForIndex(index);
        growUp();
        if (index == size) {
            add(value);
        }
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        validationForIndex(index);
        Object elemetRemove = dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index - 1);
        size--;
        return (T) elemetRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < dataArray.length; i++) {
            if (element != null
                    && element.equals(dataArray[i])
                    || element == dataArray[i]) {
                return remove(i);

            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void growUp() {
        if (dataArray.length == size) {
            Object[] newArray = new Object[(int) (dataArray.length + dataArray.length / 2)];
            System.arraycopy(dataArray,0,newArray,0, size);
            dataArray = newArray;
        }
    }

    private void validationForIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is wrong for size " + size);
        }
    }
}
