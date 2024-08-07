package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] internalArray;
    private int size;

    public ArrayList() {
        this.internalArray = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.internalArray = new Object[capacity];
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
        if (index >= 0 && index <= size) {
            shiftToRight(index);
            internalArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index. The index "
                    + "'" + index + "' doesn't exist");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) this.internalArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("The such index doesn't exist");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            internalArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong array index!");
        }
    }

    @Override
    public T remove(int index) {
        T oldelement = null;
        if (index < this.size && index >= 0) {
            oldelement = get(index);
            Object[] newFormedArray = new Object[internalArray.length];
            System.arraycopy(internalArray, 0, newFormedArray, 0, index);
            System.arraycopy(internalArray, index + 1, newFormedArray, index, (size - index) - 1);
            internalArray = newFormedArray;
            size--;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index! Index '"
                    + index + "' doesn't exist");
        }
        return oldelement;
    }

    @Override
    public T remove(T element) {
        T oldElement = null;
        for (int i = 0; i < size; i++) {
            if (internalArray[i] != null && internalArray[i].equals(element)) {
                oldElement = element;
                remove(i);
                break;
            } else if (internalArray[i] == null && internalArray[i] == element) {
                remove(i);
                break;
            }
        }
        if (oldElement == null && element != null) {
            throw new NoSuchElementException("This " + element + " wasn't found");
        }
        return oldElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] bufferArray = new Object[size + (size >> 1)];
        System.arraycopy(internalArray, 0, bufferArray, 0, size);
        internalArray = bufferArray;
    }

    private void shiftToRight(int index) {
        Object[] newBufferedArray = new Object[size + 1];
        if ((size + 1) <= internalArray.length) {
            System.arraycopy(internalArray, 0, newBufferedArray, 0, index + 1);
            System.arraycopy(internalArray, index, newBufferedArray, index + 1, size - index);
            internalArray = newBufferedArray;
        } else if ((size + 1) > internalArray.length) {
            grow();
            System.arraycopy(internalArray, 0, newBufferedArray, 0, index + 1);
            System.arraycopy(internalArray, index, newBufferedArray, index + 1, size - index);
            internalArray = newBufferedArray;
        }
    }
}
