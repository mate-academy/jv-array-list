package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MULTIPLIER_FOR_ARRAY_SIZE = 1.5;
    private int arraySize = 10;
    private T[] array;
    private int numberOfElements;

    public ArrayList() {
        array = (T[]) new Object[arraySize];
    }

    @Override
    public void add(T value) {
        if (numberOfElements >= arraySize) {
            newArray();
        }
        array[numberOfElements] = value;
        numberOfElements++;
    }

    @Override
    public void add(T value, int index) {
        if (index > numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        } else if (index < numberOfElements) {
            shiftRightAndAddElement(value, index);
            return;
        }
        array[numberOfElements] = value;
        numberOfElements++;
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
        for (int i = 0; i < numberOfElements; i++) {
            if ((array[i] == element) || (array[i] != null && array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("no such element in list");
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements < 1;
    }

    private T[] newTemporaryArray() {
        T[] tempArray = (T[]) new Object[arraySize];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        return tempArray;
    }

    private void newArray() {
        arraySize *= MULTIPLIER_FOR_ARRAY_SIZE;
        array = newTemporaryArray();
    }

    private void shiftRightAndAddElement(T value, int index) {
        if (numberOfElements >= arraySize) {
            newArray();
        }
        System.arraycopy(array, index, array, index + 1, numberOfElements - index);
        array[index] = value;
        numberOfElements++;
    }

    private void shiftLeftAndRemoveElement(int index) {
        System.arraycopy(array, index + 1, array, index, numberOfElements - index - 1);
        numberOfElements--;
    }

    private void checkIfIndexCorrect(int index) {
        if (index >= numberOfElements || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
    }
}
