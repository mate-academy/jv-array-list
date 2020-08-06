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
            makeBigger(mySize * 3 / 2);
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
            makeBigger(SIZE);
        } else {
            System.arraycopy(arrayList, index, arrayList, index + 1, arrayList.length - index - 1);
        }
        arrayList[index] = value;
        mySize++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > arrayList.length - mySize) {
            makeBigger(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < mySize) {
            return arrayList[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > mySize - 1) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {

        if (index < 0 || index > mySize - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - index - 1);
        mySize--;
        return result;
    }

    @Override
    public T remove(T t) {
        int i;
        for (i = 0; i < mySize; i++) {
            T result;
            if (t == arrayList[i] || t != null && arrayList[i].equals(t)) {
                result = arrayList[i];
                remove(i);
                return result;
            }
        }
        if (i == mySize) {
            throw new NoSuchElementException();
        }
        return null;
    }

    @Override
    public int size() {
        return mySize;
    }

    @Override
    public boolean isEmpty() {
        return mySize == 0;
    }

    public void makeBigger(int size) {
        T[] tempArray = (T[]) new Object[size];
        System.arraycopy(arrayList, 0, tempArray, 0, mySize);
        arrayList = tempArray;
    }
}


