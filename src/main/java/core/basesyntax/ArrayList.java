package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 2;
    private Object[] arrayList;
    private int size = 0;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index + "out of bound");
        }
    }

    public void newCapacity() {
        if (arrayList.length == size) {
            arrayList = Arrays.copyOf(arrayList, arrayList.length * 3 / 2);
        }
    }

    @Override
    public void add(T value) {
        newCapacity();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
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
        rangeCheckForAdd(index);
        System.out.println(arrayList[index]);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForAdd(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
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
        if (size == 0) {
            return true;
        }
        return false;
    }
}
