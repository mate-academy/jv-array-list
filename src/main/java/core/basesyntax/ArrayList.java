package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int counter = 0;
    private T[] ownArrayList;

    public ArrayList() {
        ownArrayList = (T[]) new Object[DEFAULT_SIZE];
    }

    private void resizingAndCopyingArray(int numberOfNewElement, int numbersOfElementsToAdd) {
        int newSizeArray = ownArrayList.length;
        do {
            newSizeArray = (int) (newSizeArray * 1.5);
        } while ((counter + numbersOfElementsToAdd) > newSizeArray);
        T[] newArrayList = (T[]) new Object[newSizeArray];
        System.arraycopy(ownArrayList, 0, newArrayList, 0, numberOfNewElement);
        if (counter > numberOfNewElement) {
            System.arraycopy(ownArrayList, numberOfNewElement, newArrayList, numberOfNewElement + 1,
                    ownArrayList.length - numberOfNewElement);
        }
        ownArrayList = newArrayList;
    }

    private void checkIndex(int index, int maxIndex) {
        if (index < 0 || index >= maxIndex) {
            throw new ArrayListIndexOutOfBoundsException("The index is wrong");
        }
    }

    private void countingEndWriteValue(T value, int index) {
        ownArrayList[index] = value;
        counter++;
    }

    @Override
    public void add(T value) {
        if (counter == ownArrayList.length) {
            resizingAndCopyingArray(counter, 1);
        }
        countingEndWriteValue(value, counter);
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, counter + 1);
        if (counter == ownArrayList.length) {
            resizingAndCopyingArray(index, 1);
            countingEndWriteValue(value, index);
            return;
        }
        for (int i = counter; i > index; i--) {
            ownArrayList[i] = ownArrayList[i - 1];
        }
        countingEndWriteValue(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        if ((counter + list.size()) > ownArrayList.length) {
            resizingAndCopyingArray(counter, list.size());
        }
        T [] inputArray = (T[]) new Object[list.size()];
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = list.get(i);
        }
        System.arraycopy(inputArray, 0, ownArrayList, counter, inputArray.length);
        counter = counter + list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index, counter);
        return ownArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, counter);
        ownArrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, counter);
        T removedElement = ownArrayList[index];
        for (int i = index; i < counter - 1; i++) {
            ownArrayList[i] = ownArrayList[i + 1];
        }
        counter--;
        return removedElement;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < counter; i++) {
            if (ownArrayList[i] == element
                    || ownArrayList[i] != null && ownArrayList[i].equals(element)) {
                for (int j = i; j < counter - 1; j++) {
                    ownArrayList[j] = ownArrayList[j + 1];
                }
                counter--;
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }
}
