package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] arrayList = new Object[INITIAL_SIZE];
    private int arraySize;

    public void grow(int size) {
        int newLength = size + (size >> 1);
        arrayList = Arrays.copyOf(arrayList, newLength);
    }

    @Override
    public void add(T value) {
        if (arraySize < arrayList.length) {
            arrayList[arraySize] = value;
            arraySize++;
        } else {
            grow(arrayList.length);
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("This index is out of bounds");
        }

        if (arrayList.length - (arraySize + 1) >= 0) {
            System.arraycopy(arrayList, index, arrayList, index + 1, arraySize - index);
            arrayList[index] = value;
            arraySize++;
        } else {
            grow(arrayList.length);
            add(value, index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();

        if ((arraySize + listSize) - arrayList.length <= 0) {
            for (int i = 0; i < listSize; i++) {
                arrayList[arraySize] = list.get(i);
                arraySize++;
            }
        } else {
            grow(arrayList.length);
            addAll(list);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index > arraySize - 1) {
            throw new ArrayListIndexOutOfBoundsException("No such index");
        }

        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > arraySize - 1) {
            throw new ArrayListIndexOutOfBoundsException("No such index");
        }
        arrayList[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T result = null;
        if (index < 0 || index > arraySize - 1) {
            throw new ArrayListIndexOutOfBoundsException("No such index in the list");
        }

        int remainingArray = arraySize - index - 1;
        result = (T) arrayList[index];

        if (remainingArray > 0) {
            System.arraycopy(arrayList, index + 1, arrayList, index, remainingArray);
        }
        arrayList[arraySize - 1] = null;
        arraySize--;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        T result = null;
        boolean containsArray = Arrays.asList(arrayList).contains(element);
        for (int index = 0; index < arraySize; index++) {
            if (element == null && containsArray) {
                int remainingArray = arraySize - index - 1;

                if (remainingArray > 0) {
                    System.arraycopy(arrayList, index + 1, arrayList, index, remainingArray);
                }
                arrayList[arraySize - 1] = null;
                arraySize--;
                return null;
            } else if (element != null && containsArray) {
                if (element.equals(arrayList[index])) {
                    int remainingArray = arraySize - index - 1;
                    result = (T) arrayList[index];
                    if (remainingArray > 0) {
                        System.arraycopy(arrayList, index + 1, arrayList, index, remainingArray);
                    }
                    arrayList[arraySize - 1] = null;
                    arraySize--;
                    return result;
                }
            } else {
                throw new NoSuchElementException("You've entered the wrong element");
            }
        }
        return result;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }
}
