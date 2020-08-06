package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;

    private T[] myList;
    private int sizeList;

    public ArrayList() {
        myList = (T[]) new Object[CAPACITY];
        sizeList = 0;
    }

    @Override
    public void add(T value) {
        checkResizeList();
        myList[sizeList] = value;
        sizeList += 1;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > sizeList) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index!");
        }
        checkResizeList();
        System.arraycopy(myList, index, myList, index + 1, sizeList - index);
        myList[index] = value;
        sizeList += 1;
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
        return myList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        myList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T tmpList = (T) myList[index];
        int numMoved = sizeList - index - 1;
        System.arraycopy(myList, index + 1, myList, index, numMoved);
        myList[--sizeList] = null;
        return tmpList;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < sizeList; i++) {
            if (t == myList[i] || t != null && t.equals(myList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Check value for remove!");
    }

    @Override
    public int size() {
        return sizeList;
    }

    @Override
    public boolean isEmpty() {
        return sizeList == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= sizeList) {
            throw new ArrayIndexOutOfBoundsException("Check array size!");
        }
    }

    public void checkResizeList() {
        if (sizeList >= myList.length) {
            final T[] tmpList = myList;

            myList = (T[]) new Object[(sizeList * 3) / 2 + 1];
            System.arraycopy(tmpList, 0, myList, 0, tmpList.length);
        }
    }
}
