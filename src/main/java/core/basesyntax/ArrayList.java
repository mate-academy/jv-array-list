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
        if (index < 0 || index > mySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, arrayList.length - index - 1);
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
            if (t == arrayList[i] || arrayList[i] != null && arrayList[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element dosn't exist.");
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

    private void checkConditions(int index) {
        if (index < 0 || index >= mySize) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}