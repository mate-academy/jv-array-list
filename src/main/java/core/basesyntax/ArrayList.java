package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double resizeCoefficient = 1.5;
    private Object[] elementData;
    private int numberOfObjects;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        numberOfObjects = 0;
    }

    @Override
    public void add(T value) {
        ensureCapasity();
        elementData[numberOfObjects] = value;
        numberOfObjects++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > numberOfObjects) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        ensureCapasity();

        Object[] tempFirstPart = Arrays.copyOfRange(elementData, 0, index);
        Object[] tempSecondPart = Arrays.copyOfRange(elementData, index, elementData.length);
        int counterForSecondArray = 0;
        for (int i = 0; i < elementData.length; i++) {
            if (i == index) {
                numberOfObjects++;
                elementData[i] = value;
                continue;
            }
            if (i < index) {
                elementData[i] = tempFirstPart[i];
                continue;
            }
            if (i > index) {
                elementData[i] = tempSecondPart[counterForSecondArray];
                counterForSecondArray++;
                continue;
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
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        Object[] tempFirstPart = Arrays.copyOfRange(elementData, 0, index);
        Object[] tempSecondPart = Arrays.copyOfRange(elementData, index + 1, elementData.length);
        numberOfObjects--;
        Object result = elementData[index];
        int counterForSecondArray = 0;
        for (int i = 0; i < numberOfObjects; i++) {
            if (i < tempFirstPart.length && i != index) {
                elementData[i] = tempFirstPart[i];
                continue;
            }
            if (i >= index && i < tempSecondPart.length) {
                elementData[i] = tempSecondPart[counterForSecondArray];
                counterForSecondArray++;
            }
        }
        return (T) result;
    }

    @Override
    public T remove(T element) {
        Object result = null;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null
                    ? element == elementData[i] : elementData[i].equals(element)) {
                result = elementData[i];
                remove(i);
                return (T) result;
            }
        }
        if (result == null) {
            throw new NoSuchElementException("Can`t find file " + element + " in list");
        }
        return null;
    }

    @Override
    public int size() {
        return numberOfObjects;
    }

    @Override
    public boolean isEmpty() {
        if (numberOfObjects > 0) {
            return false;
        }
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= numberOfObjects) {
            throw new ArrayListIndexOutOfBoundsException("Out of bound execution");
        }
    }

    private void ensureCapasity() {
        if (numberOfObjects >= elementData.length) {
            int newSize = (int) (numberOfObjects * resizeCoefficient);
            elementData = Arrays.copyOf(elementData, newSize);
        }
    }
}
