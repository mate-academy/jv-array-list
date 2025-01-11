package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int EMPTY_LIST_SIZE = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_ENLARGEMENT = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        this.arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = EMPTY_LIST_SIZE;
    }

    private T[] grow() {
        int capacity = arrayList.length;
        if (capacity > EMPTY_LIST_SIZE) {
            int newCapacity = (int) (capacity * ARRAY_ENLARGEMENT);
            T[] tempArray = (T[]) new Object[newCapacity];
            System.arraycopy(arrayList, 0, tempArray, 0, capacity);
            arrayList = tempArray;
            tempArray = null;
            return arrayList;
        } else {
            return arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        }
    }

    private void indexCheck(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            arrayList = grow();
        }
        arrayList[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is bigger then size");
        }
        if (size == arrayList.length) {
            arrayList = grow();
        }
        T[] tempArray = (T[]) new Object[size + 1];
        System.arraycopy(arrayList, 0, tempArray, 0, index);
        tempArray[index] = value;
        System.arraycopy(arrayList, index, tempArray, index + 1, size - index);
        arrayList = tempArray;
        tempArray = null;
        size += 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T[] tempArray = (T[]) new Object[size - 1];
        if (index == 0 || size == 1) {
            System.arraycopy(arrayList, 1, tempArray, 0, size - 1);
        }
        if (index > 0) {
            System.arraycopy(arrayList, 0, tempArray, 0, index);
            System.arraycopy(arrayList, index + 1, tempArray, index, size - index - 1);
        }
        size -= 1;
        T elementFromIndex = arrayList[index];
        arrayList = tempArray;
        tempArray = null;
        return elementFromIndex;
    }

    @Override
    public T remove(T element) throws ArrayListIndexOutOfBoundsException, NoSuchElementException {
        if (size == 0) {
            throw new ArrayListIndexOutOfBoundsException("Array is empty");
        }
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(arrayList[i])) || element == arrayList[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in this ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_LIST_SIZE;
    }
}
