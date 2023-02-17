package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity;
    private T[] listArray;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.listArray = (T[]) new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(T value, int index) {
        if ((size == 0 && index == 0) || (index >= 0 && index <= size)) {
            if (size == currentCapacity) {
                currentCapacity = currentCapacity + (currentCapacity >> 1);
                T[] temp = (T[]) new Object[currentCapacity];
                System.arraycopy(listArray, 0, temp, 0, index);
                System.arraycopy(listArray, index, temp, index + 1, size - index);
                listArray = temp;
            } else {
                if (size > 0) {
                    System.arraycopy(listArray, index, listArray, index + 1, size - index);
                }
            }
            listArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't add to index " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return listArray[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Can't get to index " + index);
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            listArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't set to index " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (size == 0 || index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove to index " + index);
        }
        T temp = listArray[index];
        if (index < size - 1) {
            System.arraycopy(listArray, index + 1, listArray, index, size - index - 1);
        }
        listArray[--size] = null;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < listArray.length; i++) {
            if (listArray[i] == element || listArray[i] != null && listArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array list");
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
