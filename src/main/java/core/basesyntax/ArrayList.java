package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private int currentSize = INITIAL_SIZE;
    private int size = 0;
    private T[] dataArray = (T[]) new Object[currentSize];

    private T[] grow() {
        double newLength = dataArray.length * 1.5;
        currentSize = (int) newLength;
        T[] newArray = (T[]) new Object[currentSize];
        for (int i = 0; i < dataArray.length; i++) {
            newArray[i] = dataArray[i];
        }
        return newArray;
    }

    @Override
    public void add(T value) {
        if (size >= currentSize) {
            dataArray = grow();
        }
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size >= currentSize) {
            dataArray = grow();
        }

        if (index > size || index >= dataArray.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index większy niż romizar tablicy");
        } else if (index <= size) {
            size++;
            for (int i = dataArray.length - 1; i > index; i--) {
                dataArray[i] = dataArray[i - 1];
            }
        }
        dataArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int newLenght = size + list.size();
        T[] result = (T[]) new Object[newLenght];

        for (int i = 0; i < size; i++) {
            result[i] = dataArray[i];
        }

        for (int i = size; i < newLenght; i++) {
            result[i] = list.get(i - size);
        }
        dataArray = result;
        size = newLenght;
    }

    @Override
    public T get(int index) {
        if (index >= size || index > dataArray.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Given index is out of bounds of array");
        }
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index > dataArray.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Given index is out of bounds of array");
        }
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            final T oldvalue = dataArray[index];
            if (index != size - 1) {
                for (int i = index; i < dataArray.length - 1; i++) {
                    dataArray[i] = dataArray[i + 1];
                }
            }
            size--;
            dataArray[size] = null;
            return oldvalue;
        }
        throw new ArrayListIndexOutOfBoundsException("Given index is out of bounds of array");
    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < dataArray.length; i++) {
            if (element != null && dataArray[i] != null && element.equals(dataArray[i])
                    || (element == null && dataArray[i] == null)) {
                dataArray[i] = null;
                for (int j = i; j < dataArray.length - 1; j++) {
                    dataArray[j] = dataArray[j + 1];
                }
                size--;
                dataArray[size] = null;
                return element;
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
}
