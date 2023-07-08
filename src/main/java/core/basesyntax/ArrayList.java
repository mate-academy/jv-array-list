package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int STOCK_ARRAY_LENGTH = 10;
    private static final float GROWTH_COEFICIENT = 1.5f;

    private T[] array = (T[]) new Object[STOCK_ARRAY_LENGTH];
    private int listSize = 0;

    @Override
    public void add(T value) {
        increaseListSizeByOne();
        array[listSize - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == listSize) {
            this.add(value);
        } else {
            checkIndexIsValid(index);

            increaseListSizeByOne();
            for (int i = lastIndex(); i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
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

        for (int i = index; i < lastIndex(); i++) {
            array[i] = array[i + 1];
        }
        listSize--;
        return actualResult;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = getIndexOf(element);
        if (indexOfElement == -1) {
            throw new NoSuchElementException("there is no " + element + " in ArrayList");
        }
        T actualResult = get(indexOfElement);
        remove(indexOfElement);
        return actualResult;
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
            changeArrayLengthTo((int)(listSize * GROWTH_COEFICIENT));
        }
    }

    private void changeArrayLengthTo(int newSize) {
        T[] previousList = array;
        array = (T[]) new Object[newSize];
        System.arraycopy(previousList, 0, array, 0, previousList.length);
    }

    private void checkIndexIsValid(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index cant be < 0");
        }
        if (index >= listSize) {
            throw new ArrayListIndexOutOfBoundsException("index cant be >= ArrayList size");
        }
    }

    private int getIndexOf(T element) {
        for (int i = 0; i < listSize - 1; i++) {
            if (array[i] == element
                    || array[i] != null && array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private int lastIndex() {
        return listSize - 1;
    }
}
