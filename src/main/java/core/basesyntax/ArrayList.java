package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayList;
    private int size = 0;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        newCapacity();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        newCapacity();
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removeElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return (T) removeElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null && arrayList[i].equals(t) || arrayList[i] == t) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index + "out of bound");
        }
    }

    public void newCapacity() {
        if (arrayList.length == size) {
            arrayList = Arrays.copyOf(arrayList, arrayList.length * 3 / 2);
        }
    }
}
