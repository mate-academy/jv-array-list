package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROW_COEFFICIENT = 1.5;

    private Object[] customArrayList = new Object[DEFAULT_SIZE];

    @Override
    public void add(T value) {
        growIfArrayFull();
        customArrayList[size()] = value;
    }

    private void growIfArrayFull() {
        if (size()==customArrayList.length) {
            int newSize = (int)(customArrayList.length*GROW_COEFFICIENT);
//            Object[] biggerArrayList = new Object[newSize];
            System.arraycopy(customArrayList, 0, customArrayList, 0, newSize);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size()) {
            return null;
        } else {
            return (T) customArrayList[index];
        }
    }

    private int findValueInArray(T value) {
        for (int i = 0; i < size(); i++) {
           if (customArrayList[i].equals(value)) {
               return i;
           }
        }
        // Element not found
        return -1;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        int size = size();
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Cannot add value to array at specified position, it is out of array");
        } else if (index == size) {
            add(value);
        } else {
            System.arraycopy(customArrayList, index, customArrayList, index+1, customArrayList.length+1);
            customArrayList[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        // For each is not working because Custom list does not implement iterable interface
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public void set(T value, int index) {
        // Set if this value exists
        if (get(index) != null) {
            customArrayList[index] = value;
        }

    }

    @Override
    public T remove(int index) {
        T value = get(index);
        if (value!=null) {
            System.arraycopy(customArrayList, index, customArrayList, index-1, customArrayList.length-1);
            return value;
        }
        // If given not valid index
        return null;
    }

    @Override
    public T remove(T element) {
        int index = findValueInArray(element);
        if (index == -1) {
            throw new NoSuchElementException("DevCap: There is no such element friend");
        } else {
            return remove(index);
        }
    }

    @Override
    public int size() {
        for (int i = 0; i < customArrayList.length; i++) {
            if (customArrayList[i] == null) {
                return i;
            }
        }
        throw new RuntimeException("DevCap: Logically program must not reach this line");
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }
}
