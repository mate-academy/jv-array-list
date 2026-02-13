package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_SCALE = 1.5;
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] objectList;
    private int size;

    public ArrayList() {
        objectList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == objectList.length) {
            grow(objectList.length + 1);
        }
        objectList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == objectList.length) {
            grow(objectList.length + 1);
        }
        System.arraycopy(objectList, index, objectList, index + 1, size - index);
        objectList[index] = value;

        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkNullException(list);
        if (objectList.length < size + list.size()) {
            grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            objectList[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexForGet(index);
        return (T) objectList[index];
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == objectList[i] || element != null && element.equals(objectList[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGet(index);
        objectList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForGet(index);
        T removeElement = get(index);
        rebuildArrayAndRemove(index);
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int removeIndex = indexOf(element);
        checkIndexValueForRemove(removeIndex);
        return remove(removeIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow(int rightSize) {
        int currentArraySize = objectList.length;

        while (currentArraySize < rightSize) {
            currentArraySize = (int) (currentArraySize * GROW_SCALE);
        }

        Object[] arrayListBuffer = new Object[currentArraySize];
        System.arraycopy(objectList, 0, arrayListBuffer, 0, size);
        objectList = arrayListBuffer;
    }

    private void rebuildArrayAndRemove(int index) {
        size--;
        System.arraycopy(objectList, index + 1, objectList, index, size - index);
        objectList[size] = null;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > (size())) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid for added");
        }
    }

    private void checkIndexForGet(int index) {
        if (index < 0 || index > (size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid for got");
        }
    }

    private void checkIndexValueForRemove(int index) {
        if (index == -1) {
            throw new NoSuchElementException("Removing element doesn't exist");
        }
    }

    private void checkNullException(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Incorrect list data, list can not be nullable");
        }
    }
}
