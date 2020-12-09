package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private T[] grow() {
        return Arrays.copyOf(arrayList, arrayList.length + arrayList.length / 2);
    }

    private boolean isIndexValid(int index) {
        if (index >= 0 && index <= size) {
            return true;
        }
        throw new ArrayIndexOutOfBoundsException("Wrong index. The index must be between"
                + " 0 and size: " + size);
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length - 1) {
            arrayList = grow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexValid(index);
        if (size == arrayList.length) {
            arrayList = grow();
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
        isIndexValid(index + 1);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index + 1);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index + 1);
        T removeValue = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == arrayList[i]) || (arrayList[i] != null && arrayList[i].equals(t))) {
                T val = remove(i);
                return val;
            }
        }
        throw new NoSuchElementException("List doesn't have this value");
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
