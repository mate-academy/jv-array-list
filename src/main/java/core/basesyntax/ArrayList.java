package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    private T[] array;
    private int sizeOfFilledArray;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ARRAY_CAPACITY];
        sizeOfFilledArray = 0;
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
        checkIndexForAdd(index);
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
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
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
        return sizeOfFilledArray;
    }

    @Override
        public boolean isEmpty() {
        return sizeOfFilledArray == 0;
    }

    private void growCapacity() {
        T[] newArray = (T[]) new Object[(int) (sizeOfFilledArray * 1.5)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void checkIndexForAdd(int index) {
        if (index > sizeOfFilledArray || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void checkIndex(int index) {
        if (index >= sizeOfFilledArray || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
