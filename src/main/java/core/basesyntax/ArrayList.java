package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] arrayList;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    private String errorOutOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private Object[] grow() {
        int oldCapacity = arrayList.length;
        if (oldCapacity > 0) {
            int newCapacity = (oldCapacity >> 1) + oldCapacity;
            return arrayList = Arrays.copyOf(arrayList, newCapacity);
        } else {
            return arrayList = new Object[DEFAULT_CAPACITY];
        }
    }

    public void ensureCapacity() {
        if (size + 1 >= arrayList.length) {
            grow();
        }
    }

    public Object[] toArray(List<T> list) {
        Object[] listToArray = new Object[list.size()];
        for (int i = 0; i < listToArray.length; i++) {
            listToArray[i] = list.get(i);
        }
        return listToArray;
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(errorOutOfBoundsMsg(index));
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheck(index);
        ensureCapacity();
        System.arraycopy(arrayList, index,
                arrayList, index + 1,
                size++ - index);
        arrayList[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listArr = toArray(list);
        System.arraycopy(listArr, 0,
                arrayList, size,
                listArr.length);
        size += listArr.length;
    }

    @Override
    public T get(int index) {
        rangeCheck(index + 1);
        return (T)arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index + 1);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T toReturn = (T)arrayList[index];
        System.arraycopy(arrayList, index + 1,
                arrayList, index,
                size-- - index);
        return toReturn;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || (arrayList[i] != null && arrayList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
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
