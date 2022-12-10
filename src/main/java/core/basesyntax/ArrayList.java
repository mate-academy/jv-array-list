package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final static int DEFAULT_CAPACITY = 10;

    private int size;
    public Object[] elementArray;

    public ArrayList() {
        this.elementArray = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow() {
        int newLength = (size * 3) / 2 + 1;
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementArray, 0, newArray, 0, size);
        return elementArray = newArray;
    }

    private Object[] cutBefore(int index) {
        Object[] cutArray = new Object[index];
        System.arraycopy(elementArray, 0, cutArray, 0, index);
        return cutArray;
    }

    private Object[] cutAfter(int index) {
        Object[] cutArray = new Object[size - index];
        System.arraycopy(elementArray, index, cutArray, 0, size - index);
        return cutArray;
    }

    @Override
    public void add(T value) {
        if (elementArray.length == size) {
            grow();
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (elementArray.length == size) {
            grow();
        }
        if (index == size) {
            add(value);
        } else if (index < size && index >= 0) {
            Object[] beforeArray = cutBefore(index);
            Object[] afterArray = cutAfter(index);
            Object[] newArray = new Object[elementArray.length];
            System.arraycopy(beforeArray, 0, newArray, 0, beforeArray.length);
            newArray[index] = value;
            System.arraycopy(afterArray, 0, newArray, index + 1, afterArray.length);
            elementArray = newArray;
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
        while (list.size() + size > elementArray.length) {
            grow();
        }
        Object[] newArray = new Object[elementArray.length];
        Object[] listArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }
        System.arraycopy(elementArray, 0, newArray, 0, size);
        System.arraycopy(listArray, 0, newArray, size, list.size());
        elementArray = newArray;
        size+= list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        }
        return (T) elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        } else {
            elementArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index exception");
        }
        T removeElement = (T) elementArray[index];
        Object[] beforeArray = cutBefore(index);
        Object[] afterArray = cutAfter(index + 1);
        Object[] newArray = new Object[elementArray.length];
        System.arraycopy(beforeArray, 0, newArray, 0, beforeArray.length);
        System.arraycopy(afterArray, 0, newArray, index, afterArray.length);
        elementArray = newArray;
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int beforeSize = size;
        for (int i = 0; i < size; i++) {
            if (elementArray[i] == null && element == null ||
                    elementArray[i] != null && elementArray[i].equals(element)) {
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
}
