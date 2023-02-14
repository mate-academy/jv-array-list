package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INCRICE = 1.5;
    private int size;
    private int capacity;
    private Object[] elementT;

    public ArrayList() {
        this.elementT = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = 0;
    }

    public void resize() {
        capacity = (int) (capacity * CAPACITY_INCRICE);
        T[] tempArray = (T[]) new Object[capacity];
        System.arraycopy(elementT, 0, tempArray, 0, size);
        elementT = tempArray;
    }

    public int getIndexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementT[i] == null && element == null)
                    || (Objects.equals((T) elementT[i], element))) {
                return i;
            }
        }
        throw new NoSuchElementException("Can't found element by value");
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on position " + index);
        }
        if (size == capacity) {
            resize();
        }
        T[] tempArray = (T[]) new Object[capacity];
        System.arraycopy(elementT, 0, tempArray, 0,index);
        tempArray[index] = value;
        System.arraycopy(elementT, index, tempArray, index + 1, size - index);
        elementT = tempArray;
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
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("No such index " + index);
        } else {
            return (T) elementT[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Can't set element on position " + index);
        } else {
            elementT[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No such index " + index);
        }
        Object[] tempArr = new Object[capacity];
        if (index == 0) {
            System.arraycopy(elementT, index + 1, tempArr, index, size - 1);
        } else if (index == size - 1) {
            System.arraycopy(elementT, 0, tempArr, 0, size - 1);
        } else {
            System.arraycopy(elementT, 0, tempArr, 0, index);
            System.arraycopy(elementT, index + 1, tempArr, index, size - index - 1);
        }
        Object temp = elementT[index];
        elementT = tempArr;
        size--;
        return (T) temp;
    }

    @Override
    public T remove(T element) {
        return remove(getIndexOf(element));
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
