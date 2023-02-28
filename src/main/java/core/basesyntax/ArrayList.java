package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    static final int INITIAL_SIZE = 10;
    private static final int INCREASE_RATE = 3 / 2 + 1;
    private int size;
    private Object[] arrayMain;

    public ArrayList() {
        arrayMain = new Object[INITIAL_SIZE];
    }

    public void arrayCopies(Object[] array1, int srcPost, Object[] array2, int destPos, int size) {
        System.arraycopy(array1, srcPost, array2, destPos, size);
    }

    @Override
    public void add(T value) {
        if (arrayMain.length == size) {
            grow();
        }
        arrayMain[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (size == arrayMain.length) {
            grow();
        }
        checkIndex(index);
        System.arraycopy(arrayMain, index, arrayMain, index + 1, size - index);
        arrayMain[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public void exclusionIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        }
    }

    @Override
    public T get(int index) {
        exclusionIndex(index);
        return (T) arrayMain[index];
    }

    @Override
    public void set(T value, int index) {
        exclusionIndex(index);
        arrayMain[index] = value;
    }

    @Override
    public T remove(int index) {
        exclusionIndex(index);
        final T removeElement = (T) arrayMain[index];
        System.arraycopy(arrayMain, index + 1, arrayMain, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int beforeSize = size;
        for (int i = 0; i < size; i++) {
            if (arrayMain[i] == null && element == null
                    || arrayMain[i] != null && arrayMain[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("element exception");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void setArrayMain(Object[] arrayMain) {
        this.arrayMain = arrayMain;
    }

    @Override
    public String toString() {
        return "ArrayList{"
                + "arrayMain=" + Arrays.toString(arrayMain)
                + ", size=" + size
                + '}';
    }

    private void grow() {
        int newSize = (int) (size * INCREASE_RATE);
        Object[] newSizeArray = new Object[newSize];
        arrayCopies(arrayMain, 0, newSizeArray, 0, size);
        arrayMain = newSizeArray;
    }

    public void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        }
    }
}
