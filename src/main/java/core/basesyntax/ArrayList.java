package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int size = 10;
    private Object [] array = new Object[size];
    private int currentSize = 0;

    @Override
    public void add(T value) {
        if (currentSize + 1 > array.length) {
            grow();
        }
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > array.length) {
            return;
        }
        int oldLength = currentSize;
        if (currentSize + 1 > array.length) {
            grow();
        }
        System.arraycopy(array, index, array,
                index + 1, oldLength - index);
        array[index] = value;
        currentSize++;

    }

    @Override
    public void addAll(List<T> list) {
        if (array.length + list.size() > 10) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= currentSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= currentSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        int oldLength = currentSize;

        checkingIndex(index);

        Object lookingObj = array[index];
        System.arraycopy(array, index + 1, array,
                index, oldLength - (index + 1));

        currentSize--;
        return (T) lookingObj;
    }

    @Override
    public T remove(T t) {
        int indexT = 0;
        for (int i = 0; i < currentSize; i++) {
            if (t == array[i] || array[i] != null && array[i].equals(t)) {
                indexT = i;
                return remove(indexT);
            }
        }
        throw new NoSuchElementException("not find ");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        int counterNull = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                counterNull++;
            }
        }
        if (counterNull == array.length - 1) {
            return true;
        }
        return currentSize == 0;
    }

    private void grow() {
        Object [] currentArray = array;
        array = new Object[(int) (array.length * 1.5)];
        System.arraycopy(currentArray, 0, array, 0, currentArray.length);
    }

    private void checkingIndex(int index) {
        if (index > array.length) {
            throw new IndexOutOfBoundsException("Index more then array size");
        }
    }
}
