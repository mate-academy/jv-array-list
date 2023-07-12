package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STOCK_ARRAY_LENGTH = 10;
    private static final float GROWTH_COEFFICIENT = 1.5f;

    private T[] array;
    private int listSize;

    public ArrayList() {
        array = (T[]) new Object[STOCK_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        increaseListSizeByOne();
        array[listSize - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == listSize) {
            add(value);
        } else {
            checkIndexIsValid(index);
            increaseListSizeByOne();
            System.arraycopy(array, index, array, index + 1, listSize - index);
            array[index] = value;
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
        checkIndexIsValid(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexIsValid(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T actualResult = get(index);
        System.arraycopy(array, index + 1, array, index, listSize - index - 1);
        listSize--;
        return actualResult;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < listSize; i++) {
            if (array[i] == element
                    || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("there is no " + element + " in ArrayList");
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    private void increaseListSizeByOne() {
        listSize++;
        if (listSize == array.length) {
            changeArrayLength((int)(listSize * GROWTH_COEFFICIENT));
        }
    }

    private void changeArrayLength(int newSize) {
        T[] previousList = array;
        array = (T[]) new Object[newSize];
        System.arraycopy(previousList, 0, array, 0, previousList.length);
    }

    private void checkIndexIsValid(int index) {
        if (index < 0 || index >= listSize) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index; index = " + index);
        }
    }
}
