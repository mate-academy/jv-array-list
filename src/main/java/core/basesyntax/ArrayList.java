package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    static final int INITIAL_SIZE = 10;
    private boolean switchNewArray = true;
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
        if (arrayMain.length == size) {
            grow();
        }
        if (index == size) {
            add(value);
        } else if (index < size && index >= 0) {
            switchNewArray = true;
            Object[] arrayFinished = newArray(index);
            arrayFinished[index] = value;
            arrayMain = arrayFinished;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        while (list.size() + size > arrayMain.length) {
            grow();
        }
        Object[] newArray = new Object[arrayMain.length];
        Object[] listArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }
        arrayCopies(arrayMain, 0, newArray, 0, size);
        arrayCopies(listArray, 0, newArray, size, list.size());

        arrayMain = newArray;
        size += list.size();
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
        switchNewArray = false;
        arrayMain = newArray(index);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int beforeSize = size;
        for (int i = 0; i < size; i++) {
            if (arrayMain[i] == null && element == null
                    || arrayMain[i] != null && arrayMain[i].equals(element)) {
                remove(i);
                break;
            }
        }
        if (beforeSize == size) {
            throw new NoSuchElementException("element exception");
        }
        return element;
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
        int newSize = (size * 3) / 2 + 1;
        Object[] newSizeArray = new Object[newSize];
        arrayCopies(arrayMain, 0, newSizeArray, 0, size);
        arrayMain = newSizeArray;
    }

    private Object[] newArray(int index) {
        int srcPos = index;
        int destPos = index + 1;
        if (!switchNewArray) {
            srcPos = 2;
            destPos = 1;
        }
        Object[] newArray = new Object[arrayMain.length];
        if (index > 0) {
            arrayCopies(arrayMain, 0, newArray, 0, index);
        }
        if (size - index > 0) {
            arrayCopies(arrayMain, srcPos, newArray, destPos, size - index);
        }
        return newArray;
    }
}


