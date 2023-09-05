package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayList.length) {
            resizeArray();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }

        checkIndex(index);

        if (size == arrayList.length) {
            resizeArray();
        }

        if (index < size) {
            System.arraycopy(arrayList, index, arrayList, index + 1, arrayList.length - index - 1);
            arrayList[index] = value;
        }
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
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        if (index < size) {
            arrayList[index] = value;
        } else if (index <= size) {
            arrayList[size] = value;
        }

    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        final T returnValue = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - index - 1);
        arrayList[size - 1] = null;
        size--;

        return returnValue;
    }

    @Override
    public T remove(T element) {
        int index = searchElement(element);
        if (index != -1) {
            remove(index);
            return element;
        } else {
            throw new NoSuchElementException("don't have this element");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        int newCapacity = arrayList.length + arrayList.length / 2;

        T[] newArrayList = (T[]) new Object[newCapacity];
        System.arraycopy(arrayList, 0, newArrayList, 0, size);
        arrayList = newArrayList;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be less than zero");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index cannot be more size");
        }
    }

    private int searchElement(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == null ? element == null : arrayList[i].equals(element)) {
                index = i;
                return index;
            }
        }
        return index;
    }
}
