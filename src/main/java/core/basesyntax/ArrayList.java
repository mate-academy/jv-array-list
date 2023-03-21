package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private static final int START_ARR_LENGTH_VALUE = 0;
    private static final int FIRST_ARR_ELEMENT = 0;
    private Object[] arrayList;
    private int arrLength;

    public ArrayList() {
        arrayList = new Object[START_CAPACITY];
        arrLength = START_ARR_LENGTH_VALUE;
    }

    private void grow() {
        Object[] grownArray = new Object[arrayList.length];
        System.arraycopy(arrayList, FIRST_ARR_ELEMENT, grownArray,
                FIRST_ARR_ELEMENT, arrayList.length);
        arrayList = new Object[grownArray.length + grownArray.length / 2];
        System.arraycopy(grownArray, FIRST_ARR_ELEMENT, arrayList,
                FIRST_ARR_ELEMENT, grownArray.length);
    }

    private void expandArray(int size) {
        if (size + 1 >= arrayList.length) {
            grow();
        }
    }

    private void exceptionThrow(int index) {
        if (index < 0 || index >= arrLength) {
            throw new ArrayListIndexOutOfBoundsException("The index exceeded the array!");
        }
    }

    @Override
    public void add(T value) {
        expandArray(arrLength);
        arrayList[arrLength] = value;
        arrLength++;
    }

    @Override
    public void add(T value, int index) {
        if (index < FIRST_ARR_ELEMENT || index > arrLength) {
            throw new ArrayListIndexOutOfBoundsException("The index exceeded the array!");
        }
        expandArray(arrLength);
        for (int i = arrLength + 1; i > index; i--) {
            arrayList[i] = arrayList[i - 1];
        }
        arrayList[index] = value;
        arrLength++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        exceptionThrow(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        exceptionThrow(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < FIRST_ARR_ELEMENT || index >= arrLength) {
            throw new ArrayListIndexOutOfBoundsException("The index exceeded the array!");
        }
        final T removedValue = (T) arrayList[index];
        for (int i = index; i < arrLength - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        arrayList[arrLength - 1] = null;
        arrLength--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrLength; i++) {
            if (element == null && arrayList[i] == null
                    || element != null && element.equals(arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element!");
    }

    @Override
    public int size() {
        return arrLength;
    }

    @Override
    public boolean isEmpty() {
        return arrLength == 0 ? true : false;
    }
}
