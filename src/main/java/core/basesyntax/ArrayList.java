package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double INCREASING_LENGTH_COEFFICIENT = 1.5;
    private static final int NOT_FOUND_ELEMENT = -1;
    private T[] data;
    private int size;

    public ArrayList() {
        this.data = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size < data.length) {
            data[size] = value;
        } else {
            int updatedDataLength = (int) (data.length * INCREASING_LENGTH_COEFFICIENT);
            T[] updatedData = (T[]) new Object[updatedDataLength];
            System.arraycopy(data, 0, updatedData, 0, size);
            updatedData[size] = value;
            this.data = updatedData;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != 0 && size != 0 && index != size && (index > size - 1 || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Impossible to assign a value to the index"
            );
        }
        if (index == 0 && size == 0) {
            this.data[0] = value;
        } else if (index == size) {
            this.data[size] = value;
        } else {
            T[] updatedData = (T[]) new Object[data.length + 1];
            System.arraycopy(data, 0, updatedData, 0, index);
            updatedData[index] = value;
            System.arraycopy(data, index, updatedData, index + 1, size - index);
            this.data = updatedData;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int addingLength = list.size();
        T[] addingArrayList = (T[]) new Object[addingLength];
        T[] updatedData = (T[]) new Object[size + addingLength];
        System.arraycopy(data, 0, updatedData, 0, size);
        for (int i = 0; i < addingLength; i++) {
            addingArrayList[i] = list.get(i);
        }
        this.data = updatedData;
        System.arraycopy(addingArrayList, 0, data, size, addingLength);
        this.size += addingLength;
    }

    @Override
    public T get(int index) {
        checkIndexOutOfBounds(index, "Impossible to get value to the pointed index");
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBounds(index, "Impossible to set value to the pointed index");
        this.data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBounds(index, "Impossible to remove value to the pointed index");
        T removingElement = data[index];
        removeElementByIndex(index);
        return removingElement;

    }

    @Override
    public T remove(T element) {
        int removingElementIndex = NOT_FOUND_ELEMENT;
        for (int i = 0; i < data.length; i++) {
            boolean isEqual = Objects.equals(element, data[i]);
            if (isEqual) {
                removingElementIndex = i;
                break;
            }
        }
        if (removingElementIndex == NOT_FOUND_ELEMENT) {
            throw new NoSuchElementException("No element in the array list");
        }
        T removingElement = data[removingElementIndex];
        removeElementByIndex(removingElementIndex);
        return removingElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void removeElementByIndex(int index) {
        T[] updatedData = (T[]) new Object[data.length - 1];
        System.arraycopy(data, 0, updatedData, 0, index);
        System.arraycopy(data, index + 1, updatedData, index, data.length - index - 1);
        this.data = updatedData;
        size--;
    }

    private void checkIndexOutOfBounds(int index, String errorMessage) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(errorMessage);
        }
    }
}
