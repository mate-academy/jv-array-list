package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] objArray;

    public ArrayList() {
        objArray = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow() {
        int oldCapacity = objArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] grown = new Object[newCapacity];
        System.arraycopy(objArray, 0, grown, 0, size);
        return grown;
    }

    private Object[] toArray(List<T> list) {
        int size = list.size();
        Object[] listToArr = new Object[size];
        for (int i = 0; i < size; i++) {
            listToArr[i] = list.get(i);
        }
        return listToArr;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size == objArray.length) {
            objArray = grow();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index < size) {
            int sizeOfTempArr = size - index;
            Object[] temporary = new Object[sizeOfTempArr];
            System.arraycopy(objArray, index, temporary, 0, sizeOfTempArr);
            objArray[index] = value;
            System.arraycopy(temporary, 0, objArray, index + 1, sizeOfTempArr);
            size++;
            return;
        }
        objArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] fromList = toArray(list);
        for (Object obj : fromList) {
            add((T) obj);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) objArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objArray[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedItem = (T) objArray[index];
        int sizeOfTempArr = size - index - 1;
        Object[] temporary = new Object[sizeOfTempArr];
        System.arraycopy(objArray, index + 1, temporary, 0, sizeOfTempArr);
        System.arraycopy(temporary, 0, objArray, index, sizeOfTempArr);
        objArray[--size] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == objArray[i] || element != null && element.equals(objArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There no such element in array");
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
