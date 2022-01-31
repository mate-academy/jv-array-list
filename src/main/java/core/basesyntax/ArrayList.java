package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;
    public static final int ARRAY_START_INDEX = 0;
    private int capacity;
    private int actualSizeOfDataArray;
    private T[] dataArray;

    public ArrayList() {
        capacity = INITIAL_CAPACITY;
        dataArray = (T[]) new Object[capacity];
    }

    private void grow() {
        capacity += capacity / 2;
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(dataArray, ARRAY_START_INDEX, temp, 0, dataArray.length);
        dataArray = temp;
    }

    private void movePartToRightFrom(int index) {
        if (isCapacityFull()) {
            grow();
        }
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(dataArray, ARRAY_START_INDEX, temp, ARRAY_START_INDEX, index);
        System.arraycopy(dataArray, index, temp, (index + 1), (dataArray.length - index));
        dataArray = temp;
    }

    private void movePartToLeftFrom(int index) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(dataArray, ARRAY_START_INDEX, temp, ARRAY_START_INDEX, index);
        System.arraycopy(dataArray, index + 1, temp, index, (dataArray.length - index - 1));
        dataArray = temp;
    }

    private boolean isCapacityFull() {
        return actualSizeOfDataArray + 1 >= capacity;
    }

    private boolean isIndexCorrect(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is less than 0");
        }
        if (index > actualSizeOfDataArray) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds of the dataArray");
        }
        return index > 0 && index <= actualSizeOfDataArray;
    }

    @Override
    public void add(T value) {
        if(isCapacityFull()) {
            grow();
        }
        dataArray[actualSizeOfDataArray] = value;
        actualSizeOfDataArray++;
    }

    @Override
    public void add(T value, int index) {
        if (isIndexCorrect(index)) {
            if (index < actualSizeOfDataArray) {
                movePartToRightFrom(index);
                dataArray[index] = value;
            }
            if (index == actualSizeOfDataArray) {
                add(value);
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if(isIndexCorrect(index)) {
            return dataArray[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (isIndexCorrect(index)) {
            if (index < actualSizeOfDataArray) {
                dataArray[index] = value;
            }
            if (index == actualSizeOfDataArray) {
                throw new ArrayListIndexOutOfBoundsException("cannot change non existing variable in a list," +
                        " use add() method for this operation");
            }
        }
    }

    @Override
    public T remove(int index) {
        T result;
        if (isIndexCorrect(index)) {
            if (index < actualSizeOfDataArray) {
                result = dataArray[index];
                movePartToLeftFrom(index);
                return result;
            }
            if (index == actualSizeOfDataArray) {
                throw new NoSuchElementException("cannot remove non existing variable in a list");
            }
        }
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("cannot remove non existing variable in a list");
    }

    @Override
    public int size() {
        return actualSizeOfDataArray;
    }

    @Override
    public boolean isEmpty() {
        return actualSizeOfDataArray == 0;
    }
}
