package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final Double MULTIPLIER = 1.5;
    private Object[] internalArray;
    private int size;

    public ArrayList() {
        this.internalArray = new Object[10];
    }

    @Override
    public void add(T value) {
        grow();
        internalArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist, size of array = "
                    + size);
        }
        grow();
        if (size == index) {
            internalArray[size] = value;
        } else {
            Object[] internalArrayTemp = new Object[internalArray.length];
            System.arraycopy(internalArray,0, internalArrayTemp, 0, index);
            System.arraycopy(internalArray,index, internalArrayTemp, index + 1, size - index);
            internalArrayTemp[index] = value;
            internalArray = internalArrayTemp;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List<> shouldn't be null");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (index < internalArray.length) {
            Object[] internalArrayTemp = new Object[internalArray.length];
            size = size - 1;
            System.arraycopy(internalArray,0, internalArrayTemp, 0, index);
            System.arraycopy(internalArray,index + 1, internalArrayTemp, index,
                    internalArray.length - index - 1);
            T removableElement = (T) internalArray[index];
            internalArray = internalArrayTemp;
            return removableElement;
        }
        throw new ArrayListIndexOutOfBoundsException("Element with index " + index
                + " doesn't exist. Size of array is only " + size);
    }

    @Override
    public T remove(T element) {
        Object[] internalArrayTemp = new Object[internalArray.length];
        for (int i = 0; i < size; i++) {
            if (element == (T) internalArray[i] || (element != null
                    && element.equals((T) internalArray[i]))) {
                System.arraycopy(internalArray,0, internalArrayTemp, 0, i);
                System.arraycopy(internalArray,i + 1, internalArrayTemp, i,
                        size - i - 1);
                T removeThisElement = (T) internalArray[i];
                size = size - 1;
                internalArray = internalArrayTemp;
                return removeThisElement;
            }
        }
        throw new NoSuchElementException("Element " + element + " doesn't exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == internalArray.length) {
            int newLength = (int) (internalArray.length * MULTIPLIER);
            Object[] internalArrayTemp = new Object[newLength];
            System.arraycopy(internalArray,0, internalArrayTemp, 0, internalArray.length);
            internalArray = internalArrayTemp;
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be negative");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element with index " + index
                    + " doesn't exist. Size of array is only " + size);
        }
    }
}
