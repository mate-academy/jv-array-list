package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAYLIST_SIZE = 15;
    private int listSize;
    private int lastRecord = 0;

    private T[] listArray = (T[])(new Object[DEFAULT_ARRAYLIST_SIZE]);

    public ArrayList() {
        listSize = DEFAULT_ARRAYLIST_SIZE;
    }

    private void growArray(int records) {
        T[] tempArray = (T[])(new Object[listArray.length]);
        for (int i = 0; i < listArray.length; i++) {
            tempArray[i] = listArray[i];
        }
        listArray = (T[])(new Object[listArray.length + records]);
        for (int i = 0; i < tempArray.length; i++) {
            listArray[i] = tempArray[i];
        }
    }

    @Override
    public void add(T value) {
        if (lastRecord + 1 > listArray.length) {
            growArray(listArray.length >> 1);
        }
        listArray[lastRecord] = value;
        lastRecord++;
    }

    @Override
    public void add(T value, int index) {
        if (index > lastRecord || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
        if (lastRecord + 1 > listArray.length) {
            growArray(listArray.length >> 1);
        }
        for (int i = listArray.length - 1; i > index; i--) {
            listArray[i] = listArray[i - 1];
        }
        listArray[index] = value;
        lastRecord++;
    }

    @Override
    public void addAll(List<T> list) {
        if (lastRecord + list.size() > listArray.length) {
            growArray(listArray.length + list.size() + listArray.length >> 1);
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= lastRecord || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
        return listArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= lastRecord || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
        listArray[index] = value;
        if (index > lastRecord) {
            lastRecord = index;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= lastRecord || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
        T result = listArray[index];
        for (int i = index; i < listArray.length - 1; i++) {
            listArray[i] = listArray[i + 1];
        }
        lastRecord--;
        return result;
    }

    @Override
    public T remove(T element) {
        boolean isFound = false;
        for (int i = 0; i < lastRecord; i++) {
            if (listArray[i] == element || element != null && element.equals(listArray[i])) {
                remove(i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public int size() {
        return lastRecord;
    }

    @Override
    public boolean isEmpty() {
        if (lastRecord == 0) {
            return true;
        }
        return false;
    }
}
