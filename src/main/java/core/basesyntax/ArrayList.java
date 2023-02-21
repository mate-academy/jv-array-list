package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MULTIPLIER_FOR_ARRAY_SIZE = 1.5;
    private int arrayCapacity = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[arrayCapacity];
    }

    @Override
    public void add(T value) {
        if (size >= arrayCapacity) {
            newArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        } else if (index < size) {
            shiftRightAndAddElement(value, index);
            return;
        }
        array[size] = value;
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
        checkIfIndexCorrect(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexCorrect(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexCorrect(index);
        T temp = array[index];
        shiftLeftAndRemoveElement(index);
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == element) || (array[i] != null && array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("no such element in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    private T[] newTemporaryArray() {
        T[] tempArray = (T[]) new Object[arrayCapacity];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        return tempArray;
    }

    private void newArray() {
        arrayCapacity *= MULTIPLIER_FOR_ARRAY_SIZE;
        array = newTemporaryArray();
    }

    private void shiftRightAndAddElement(T value, int index) {
        if (size >= arrayCapacity) {
            newArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void shiftLeftAndRemoveElement(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    private void checkIfIndexCorrect(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
    }
}
