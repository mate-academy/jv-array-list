package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private Object[] list = new Object[ARRAY_SIZE];
    private int arraySize;

    @Override
    public void add(T value) {
        if (arraySize == list.length) {
            list = resize();
        }
        if (value == null) {
            arraySize++;
            return;
        }
        list[arraySize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index of bound exception");
        }
        if (arraySize == list.length) {
            list = resize();
        }
        System.arraycopy(list,index,list,index + 1, arraySize - 1);
        list[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > arraySize - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index of bound exception");
        }
        return (T)list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index of bound exception");
        }
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index of bound exception");
        }
        T removedValue = (T)list[index];
        System.arraycopy(list, index + 1, list, index, arraySize - index);
        arraySize--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (element == list[i] || element != null && element.equals(list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private T[] resize() {
        T[] array = (T[])new Object[(arraySize * 3) / 2 + 1];
        System.arraycopy(list, 0, array, 0, arraySize);
        return array;
    }
}
