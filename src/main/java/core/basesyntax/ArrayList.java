package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        arrayList[size] = value;
        size++;
        if (size == arrayList.length) {
            growArrayIfItIsFull();
        }
    }

    @Override
    public void add(T value, int index) {
        outOfBoundsCheckForMethodAddByIndex(index);
        if (arrayList.length - size == 0) {
            growArrayIfItIsFull();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        outOfBoundsCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        outOfBoundsCheck(index);
        T result = arrayList[index];
        dataTransferWhenRemove(index + 1, index);
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && arrayList[i] == null)
                    || (element != null && element.equals(arrayList[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private void growArrayIfItIsFull() {
        int incrementStep = arrayList.length / 2;
        T[] arrayListTemp = (T[]) new Object[arrayList.length + incrementStep];
        System.arraycopy(arrayList, 0, arrayListTemp, 0, size);
        arrayList = (T[]) new Object[arrayListTemp.length];
        System.arraycopy(arrayListTemp, 0, arrayList, 0, arrayListTemp.length);
    }

    private void outOfBoundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "the index is not valid");
        }
    }

    private void outOfBoundsCheckForMethodAddByIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "the index is not valid");
        }
    }

    private void dataTransferWhenRemove(int index1, int index2) {
        System.arraycopy(arrayList, index1, arrayList, index2, size - index2);
        arrayList[size - 1] = null;
        size--;
    }
}
