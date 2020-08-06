package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int SIZE = 10;
    private int mySize;
    private T[] arrayList;

    public ArrayList() {
        mySize = 0;
        arrayList = (T[]) new Object[SIZE];
    }

    @Override
    public void add(T value) {
        if (arrayList.length == mySize) {
            makeBigger(arrayList.length);
        }
        arrayList[mySize] = value;
        mySize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > mySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (arrayList.length <= index) {
            makeBigger(arrayList.length);
        } else {
            System.arraycopy(arrayList, index, arrayList, index + 1, arrayList.length - index - 1);
        }
        arrayList[index] = value;
        mySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkConditions(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkConditions(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkConditions(index);
        T result = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - index - 1);
        mySize--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < mySize; i++) {
            T result;
            if (t == arrayList[i] || arrayList[i] != null && arrayList[i].equals(t)) {
                result = arrayList[i];
                remove(i);
                return result;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return mySize;
    }

    @Override
    public boolean isEmpty() {
        return mySize == 0;
    }

    private void makeBigger(int size) {
        T[] tempArray = (T[]) new Object[size * 3 / 2];
        System.arraycopy(arrayList, 0, tempArray, 0, mySize);
        arrayList = tempArray;
    }

    private void checkConditions(int index) {
        if (index < 0 || index >= mySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}


