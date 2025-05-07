package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final int ONE_ELEMENT = 1;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkLength();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkLength();
        checkAddIndex(index);
        System.arraycopy(arrayList, index, arrayList,
                index + ONE_ELEMENT, size - index);
        arrayList[index] = value;
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
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = arrayList[index];
        System.arraycopy(arrayList, index + ONE_ELEMENT,
                arrayList, index, size - index - ONE_ELEMENT);
        arrayList[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i] || (element != null && element.equals(arrayList[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element does not exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] extendedList = (T[]) new Object[size + (size / 2)];
        System.arraycopy(arrayList,0, extendedList,0, size);
        arrayList = extendedList;
        return arrayList;
    }

    private void checkLength() {
        if (size == arrayList.length) {
            grow();
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of length " + size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of length " + size);
        }
    }
}
