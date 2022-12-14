package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] arrayMain = new Object[10];
    private int size = 0;

    private void grow() {
        int newSize = (size * 3) / 2 + 1;
        Object[] newArray = new Object[newSize];
        System.arraycopy(arrayMain, 0, newArray, 0, size);
        arrayMain = newArray;
    }

    private Object[] cutBefore(int index) {
        Object[] cutArray1 = new Object[index];
        System.arraycopy(arrayMain, 0, cutArray1, 0, index);
        return cutArray1;
    }

    private Object[] cutAfter(int index) {
        Object[] cutArray2 = new Object[size - index];
        System.arraycopy(arrayMain, index, cutArray2, 0, size - index);
        return cutArray2;
    }

    private Object[] newArray(Object[] before, Object[] after, int index) {
        Object[] newArray = new Object[arrayMain.length];
        System.arraycopy(before, 0, newArray, 0, before.length);
        System.arraycopy(after, 0, newArray, index + 1, after.length);
        return newArray;
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
            Object[] arrayFinished = newArray(cutBefore(index), cutAfter(index), index);
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
        System.arraycopy(arrayMain, 0, newArray, 0, size);
        System.arraycopy(listArray, 0, newArray, size, list.size());
        arrayMain = newArray;
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        }
        return (T) arrayMain[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        } else {
            arrayMain[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        }
        final T removeElement = (T) arrayMain[index];
        arrayMain = newArray(cutBefore(index), cutAfter(index + 1), index - 1);
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
}

