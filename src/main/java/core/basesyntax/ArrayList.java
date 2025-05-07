package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] arrayList = new Object[INITIAL_SIZE];
    private int arraySize;

    private void checkIndex(int index) {
        if (index < 0 || index > arraySize - 1) {
            throw new ArrayListIndexOutOfBoundsException("No such index");
        }
    }

    private void grow() {
        Object[] arrayCopy = new Object[arrayList.length];
        System.arraycopy(arrayList, 0, arrayCopy, 0, arrayList.length);
        Arrays.fill(arrayList, null);
        int newLength = arrayList.length + (arrayList.length >> 1);
        arrayList = new Object[newLength];
        System.arraycopy(arrayCopy, 0, arrayList, 0, arrayCopy.length);
    }

    @Override
    public void add(T value) {
        if (arraySize >= arrayList.length) {
            grow();
        }
        arrayList[arraySize] = value;
        arraySize++;
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
            grow();
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
            grow();
            addAll(list);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);

        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T result = null;
        checkIndex(index);

        int remainingArray = arraySize - index - 1;
        result = (T) arrayList[index];

        if (remainingArray > 0) {
            System.arraycopy(arrayList, index + 1, arrayList, index, remainingArray);
        }
        arrayList[--arraySize] = null;
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        for (int index = 0; index < arraySize; index++) {
            if ((element != null && element.equals(arrayList[index]))
                    || (element == arrayList[index])) {
                return remove(index);
            }
        }

        throw new NoSuchElementException("You've entered the wrong element");
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

