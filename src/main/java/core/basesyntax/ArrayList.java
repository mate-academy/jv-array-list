package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double COEFFICIENT = 1.5;
    private static final int NOT_FOUND_ELEMENT = -1;
    private T[] data;
    private int size = 0;

    public ArrayList() {
        this.data = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size < data.length) {
            data[size] = value;
        } else {
            int updatedDataLength = (int) (data.length * COEFFICIENT);
            T[] updatedData = (T[]) new Object[updatedDataLength];
            for (int i = 0; i < data.length; i++) {
                updatedData[i] = data[i];
            }
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
            for (int i = 0; i < updatedData.length; i++) {
                if (i < index) {
                    updatedData[i] = data[i];
                }
                if (i == index) {
                    updatedData[i] = value;
                    break;
                }
            }
            T[] secondPart = (T[]) new Object[data.length - index];
            int counter = index;
            for (int i = 0; i < secondPart.length; i++) {
                secondPart[i] = data[counter];
                counter++;
            }
            this.data = updatedData;
            System.arraycopy(secondPart, 0, this.data, index + 1, secondPart.length);
        }
        size++;
    }

    @Override
    public String toString() {
        return "ArrayList{"
                + "data=" + Arrays.toString(data)
                + ", size=" + size + '}';
    }

    @Override
    public void addAll(List<T> list) {
        int addingLength = list.size();
        T[] addingArrayList = (T[]) new Object[addingLength];
        T[] updatedData = (T[]) new Object[size + addingLength];
        for (int i = 0; i < size; i++) {
            updatedData[i] = data[i];
        }
        for (int i = 0; i < addingLength; i++) {
            addingArrayList[i] = list.get(i);
        }
        this.data = updatedData;
        System.arraycopy(addingArrayList, 0, data, size, addingLength);
        this.size += addingLength;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Impossible to get value to the pointed index"
            );
        }
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Impossible to set value to the pointed index"
            );
        } else {
            this.data[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Impossible to remove value to the pointed index"
            );
        } else {
            T removingElement = data[index];
            removeElement(index);
            return removingElement;
        }
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
        removeElement(removingElementIndex);
        return removingElement;
    }

    private void removeElement(int index) {
        T[] updatedData = (T[]) new Object[data.length - 1];
        for (int i = 0; i < updatedData.length; i++) {
            if (i < index) {
                updatedData[i] = data[i];
            } else {
                updatedData[i] = data[i + 1];
            }
        }
        this.data = updatedData;
        size--;
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
