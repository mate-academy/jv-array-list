package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] internalArray;
    private int size;

    public ArrayList() {
        internalArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        internalArray = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size < internalArray.length) {
            internalArray[size] = value;
            size++;
        } else {
            grow();
            internalArray[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index! Index '"
                    + index + "' doesn't exist");
        }
        shiftToRight(index);
        internalArray[index] = value;
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
        return (T) this.internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldelement = get(index);
        System.arraycopy(internalArray, index + 1, internalArray, index, size - (index + 1));
        size--;
        return oldelement;
    }

    @Override
    public T remove(T element) {
        T oldElement = null;
        for (int i = 0; i < size; i++) {
            if (internalArray[i] != null && internalArray[i].equals(element)
                    || internalArray[i] == null && internalArray[i] == element) {
                oldElement = element;
                return remove(i);
            }
        }
        if (oldElement != element) {
            throw new NoSuchElementException("This " + element + " wasn't found");
        }
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index! Index '"
                    + index + "' doesn't exist");
        }
        return true;
    }

    private void grow() {
        Object[] bufferedArray = new Object[size + (size >> 1)];
        copyArrayMethod(bufferedArray);
    }

    private void copyArrayMethod(Object[] bufferedArray) {
        System.arraycopy(internalArray, 0, bufferedArray, 0, size);
        internalArray = (T[]) bufferedArray;
    }

    private void shiftToRight(int index) {
        if ((size + 1) <= internalArray.length) {
            System.arraycopy(internalArray, index, internalArray, index + 1, size - index);
        } else if ((size + 1) > internalArray.length) {
            grow();
            System.arraycopy(internalArray, index, internalArray, index + 1, size - index);
        }
    }
}
