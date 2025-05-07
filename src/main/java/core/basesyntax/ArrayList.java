package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        ++size;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);
        elements[index] = value;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > elements.length - size) {
            grow();
        }
        for (int index = 0; index < list.size(); ++index) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        Object item = elements[index];
        System.arraycopy(elements, index + 1, elements, index, --size - index);
        return (T) item;
    }

    @Override
    public T remove(T element) {
        Object item = null;
        int index = 0;
        for (; index < size; ++index) {
            if (elements[index] == element || elements[index] != null
                    && elements[index].equals(element)) {
                item = remove(index);
                break;
            }
        }
        if (index == size) {
            throw new NoSuchElementException("There is no element like this!!!");
        }
        return (T) item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newSize = (int) (elements.length * 1.5);
        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }
}
