package core.basesyntax;

import edu.emory.mathcs.backport.java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    Object [] elements;
    private int size = 0;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkRange(index);
        if (elements.length == size) {
            increaseArraySize();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listData = (T[]) new Object[list.size()];
        for (int i = 0; i < listData.length; i++) {
            listData[i] = list.get(i);
        }
        System.arraycopy(listData,0, elements,size, listData.length);
        size += listData.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T removed = (T) elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return removed;
    }

    @Override
    public T remove(T t) {
        int index = getIndexElement(t);

        if (index == -1) {
            return null;
        }
        if (isExist(t)) {
            throw new NoSuchElementException("Such element: " + t
                                                + " doesn't contain in this list.");
        }
        T removed = (T) elements[index];
        for (int i = index; i < size; i++) {
            if (elements[i] != null && elements[i].equals(removed)) {
                elements[i] = elements[i + 1];
            }
        }
        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArraySize() {
        int newSize = (elements.length * 3) / 2 + 1;
        elements = Arrays.copyOf(elements, newSize);
    }

    private int getIndexElement(T t) {
        int index = 0;
        for (int k = 0; k < elements.length; k++) {
            if (t == null) {
                size--;
                return -1;
            }
            if (t.equals(elements[k])) {
                index = k;
                break;
            }
        }
        return index;
    }

    private void checkRange(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This " + index
                                                    + " is out of range, size: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This index " + index
                                                    + " is out of bound" + "size: " + size);
        }
    }

    private boolean isExist(T value) {
        int count = 0;
        for (int i = 0; i < elements.length; i++) {
            if (value.equals(elements[i])) {
                count++;
            }
        }
        return count == 0;
    }
}
