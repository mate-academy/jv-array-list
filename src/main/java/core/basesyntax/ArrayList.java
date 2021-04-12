package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_OF_ARRAY = 10;
    private static final double GROWING_VALUE = 1.5;
    private int sizeOfList;
    private T[] arrayList;

    public ArrayList() {
        this.arrayList = (T[]) new Object[SIZE_OF_ARRAY];
    }

    @Override
    public void add(T value) {
        if (arrayList.length == sizeOfList) {
            listCreator();
        }
        arrayList[sizeOfList] = value;
        sizeOfList++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > sizeOfList) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound Exception");
        }
        if (sizeOfList == arrayList.length) {
            listCreator();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, sizeOfList - index);
        arrayList[index] = value;
        sizeOfList++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index,sizeOfList);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,sizeOfList);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, sizeOfList);
        T removedElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, sizeOfList - index - 1);
        sizeOfList--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int indexOfArray = -1;
        for (int i = 0; i < sizeOfList; i++) {
            if ((arrayList[i] == element) || arrayList[i] != null
                    && arrayList[i].equals(element)) {
                indexOfArray = i;
                break;
            }
        }
        
        if (indexOfArray == -1) {
            throw new NoSuchElementException("No Such Element");
        }
        return remove(indexOfArray);
    }

    @Override
    public int size() {
        return sizeOfList;
    }

    @Override
    public boolean isEmpty() {
        return sizeOfList == 0;
    }

    private void listCreator() {
        T[] newList = (T[]) new Object[(int) (arrayList.length * GROWING_VALUE)];
        System.arraycopy(arrayList, 0, newList, 0, arrayList.length);
        arrayList = newList;
    }

    private void checkIndex(int index, int maxEvailableSize) {
        if (index >= maxEvailableSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound Exception");
        }
    }
}
