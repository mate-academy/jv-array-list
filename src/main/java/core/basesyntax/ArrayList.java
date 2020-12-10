package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    private T[] array;
    private int sizeOfFilledArray;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_ARRAY_CAPACITY];
        this.sizeOfFilledArray = 0;
    }

    public void growCapacity() {
        T[] newArray = (T[]) new Object[sizeOfFilledArray + DEFAULT_ARRAY_CAPACITY / 2];
        System.arraycopy(array,0, newArray,0, array.length);
        array = newArray;

    }

    @Override
    public void add(T value) {
        if (sizeOfFilledArray == array.length) {
            growCapacity();
        }
        array[sizeOfFilledArray] = value;
        sizeOfFilledArray++;
    }

    @Override
    public void add(T value, int index) {
        indexValidationCheckAdd(index);
        if (sizeOfFilledArray == array.length) {
            growCapacity();
        }
        if (index == sizeOfFilledArray) {
            add(value);
            return;
        }
        System.arraycopy(array, index, array, index + 1, sizeOfFilledArray - index);
        array[index] = value;
        sizeOfFilledArray++;
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T temp = array[index];
        System.arraycopy(array, index + 1, array, index, sizeOfFilledArray - index - 1);
        sizeOfFilledArray--;
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < sizeOfFilledArray; i++) {
            if (t == array[i] || t != null && t.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in array!");
    }

    @Override
        public int size() {
        return this.sizeOfFilledArray;
    }

    @Override
        public boolean isEmpty() {
        return this.sizeOfFilledArray == 0;
    }

    public void indexValidationCheckAdd(int index) {
        if (index > sizeOfFilledArray || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void indexCheck(int index) {
        if (index >= sizeOfFilledArray || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
