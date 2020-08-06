package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int mySize;
    private T[] arrayList;

    public ArrayList() {
        mySize = 0;
        arrayList = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSize(arrayList.length);
        arrayList[mySize] = value;
        mySize++;
    }

    @Override
    public void add(T value, int index) {
        checkConditions(index, mySize + 1);
        checkSize(index);
        resizeArray(index, index + 1);
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
        checkConditions(index, mySize);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkConditions(index, mySize);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkConditions(index, mySize);
        T result = arrayList[index];
        resizeArray(index + 1, index);
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

    private void checkSize(int check) {
        if (check == mySize) {
            T[] tempArray = (T[]) new Object[arrayList.length * 3 / 2];
            System.arraycopy(arrayList, 0, tempArray, 0, mySize);
            arrayList = tempArray;
        }
    }

    private void checkConditions(int index, int checkSize) {
        if (index < 0 || index >= checkSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void resizeArray(int firstBegin, int secondBegin) {
        System.arraycopy(arrayList, firstBegin, arrayList, secondBegin,
                arrayList.length - Math.min(firstBegin,secondBegin) - 1);
    }
}


