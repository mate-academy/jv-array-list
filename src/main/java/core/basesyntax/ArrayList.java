package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_MULTIPLIER = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
    }

    private void increaseCapacity() {
        T[] newArray = (T[]) new Object[(int) (size * SIZE_MULTIPLIER)];
        System.arraycopy(arrayList, 0, newArray, 0, size);
        arrayList = newArray;
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            increaseCapacity();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index '" + index + "' is not valid.");
        }
        if (arrayList.length == size) {
            increaseCapacity();
        }
        System.arraycopy(arrayList,index, arrayList, index + 1, size - index);
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
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = arrayList[index];
        System.arraycopy(arrayList,index + 1, arrayList, index, size - 1 - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, arrayList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Cant remove this element" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
