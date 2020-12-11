package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_ARRAY = 10;
    private static int lastElementIndex;
    private T [] arrayList;

    public ArrayList() {
        arrayList = (T[]) new Object[SIZE_ARRAY];
        lastElementIndex = -1;
    }

    private void extendCapacity() {
        int newSize = arrayList.length + (arrayList.length >> 1);
        T [] moreSizeArrayList = (T[]) new Object[newSize];
        System.arraycopy(arrayList, 0, moreSizeArrayList, 0, lastElementIndex + 1);
        arrayList = moreSizeArrayList;
    }

    private void checkOutOfBoundsArray(int index) {
        if (index < 0 || index > lastElementIndex) {
            throw new ArrayIndexOutOfBoundsException("index " + index
                    + " is out of bounds of array");
        }
    }

    @Override
    public void add(T value) {
        if (lastElementIndex + 1 >= arrayList.length) {
            extendCapacity();
        }
        lastElementIndex++;
        arrayList[lastElementIndex] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > lastElementIndex + 1) {
            throw new ArrayIndexOutOfBoundsException("index " + index
                    + " is out of bounds of array");
        }
        if (index == lastElementIndex + 1) {
            add(value);
            return;
        }
        if (index <= lastElementIndex) {
            int sizeArray = lastElementIndex + 1 + 1;
            T [] arrayCopy = (T[]) new Object[sizeArray];
            if (index != 0) {
                System.arraycopy(arrayList, 0, arrayCopy, 0, index);
            }
            arrayCopy[index] = value;
            System.arraycopy(arrayList, index, arrayCopy, index + 1, lastElementIndex + 1 - index);
            arrayList = arrayCopy;
            lastElementIndex = arrayList.length - 1;
        }
    }

    @Override
    public void addAll(List<T> list) {
        ArrayList<T> castedList = (ArrayList<T>) list;
        while (list.size() + this.size() > arrayList.length) {
            extendCapacity();
        }
        System.arraycopy(castedList.arrayList,0, arrayList, this.size() + 1, list.size());
        lastElementIndex = list.size() + this.size();
    }

    @Override
    public T get(int index) {
        checkOutOfBoundsArray(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBoundsArray(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutOfBoundsArray(index);
        T [] arrayCopy = (T[]) new Object[lastElementIndex];
        System.arraycopy(arrayList, 0, arrayCopy, 0, index);
        System.arraycopy(arrayList, index + 1, arrayCopy, index, lastElementIndex - index);
        T memValue = arrayList[index];
        arrayList = arrayCopy;
        lastElementIndex--;
        return memValue;
    }

    @Override
    public T remove(T t) {
        int index;
        for (index = 0; index <= lastElementIndex; index++) {
            if (t == null && arrayList[index] == null || arrayList[index].equals(t)) {
                break;
            }
        }
        if (index - 1 == lastElementIndex && arrayList[index] != t) {
            throw new NoSuchElementException("Dont have such value in this array");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return lastElementIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        if (lastElementIndex == -1) {
            return true;
        }
        return false;
    }
}
