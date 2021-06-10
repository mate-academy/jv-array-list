package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] arrayList;

    public ArrayList() {
        arrayList = new Object[DEFAULT_CAPACITY];
    }

    public Object[] toArray(List<T> list) {
        Object[] listToArray = new Object[list.size()];
        for (int i = 0; i < listToArray.length; i++) {
            listToArray[i] = list.get(i);
        }
        return listToArray;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        arrayList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(arrayList, index,
                arrayList, index + 1,
                size++ - index);
        arrayList[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listArr = toArray(list);
        for (int i = 0; i < list.size(); i ++) {
            add((T) listArr[i]);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index + 1);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T returnValue = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1,
                arrayList, index,
                --size - index);
        return returnValue;
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

    private void ensureCapacity() {
        if (size + 1 > arrayList.length) {
            grow();
        }
    }

    private String getErrorOutOfBoundsMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private Object[] grow() {
        int oldCapacity = arrayList.length;
        int newCapacity = (oldCapacity >> 1) + oldCapacity;
        Object[] newArrayList = new Object[newCapacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        return arrayList = newArrayList;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(getErrorOutOfBoundsMessage(index));
        }
    }
}
