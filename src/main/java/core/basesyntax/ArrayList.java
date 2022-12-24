package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] internalArray = new Object[10];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == internalArray.length) {
            int newLength = (int) (internalArray.length * 1.5);
            Object[] internalArrayTemp = new Object[newLength];
            System.arraycopy(internalArray,0, internalArrayTemp, 0, internalArray.length);
            internalArray = internalArrayTemp;
        }
        internalArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist, size of array = "
                    + size);
        }
        if (size == internalArray.length) {
            int newLength = (int) (internalArray.length * 1.5);
            Object[] internalArrayTemp = new Object[newLength];
            System.arraycopy(internalArray,0, internalArrayTemp, 0, internalArray.length);
            internalArray = internalArrayTemp;
        }
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
        if (list.size() + size > internalArray.length) {
            Object[] internalArrayTemp = new Object[list.size() + size];
            System.arraycopy(internalArray,0, internalArrayTemp, 0, size);
            System.arraycopy(toArray(list),0, internalArrayTemp, size, list.size());
            internalArray = internalArrayTemp;
            size = internalArray.length;
            return;
        }
        System.arraycopy(toArray(list),0, internalArray, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be negative");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element with index " + index
                    + " doesn't exist. Size of array is only " + size);
        }
        return (T) internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be negative");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Element with index " + index
                    + " doesn't exist. Size of array is only " + size);
        }
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be negative");
        }
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
            if (equals(element, (T) internalArray[i])) {
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

    private Object[] toArray(List<T> list) {
        Object[] result = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private boolean equals(T value1, T value2) {
        return value1 == value2 || (value1 != null && value1.equals(value2));
    }
}
