package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CONFIRMED_SIZE = 10;
    private int size = 0;
    private Object[] listArray;

    public ArrayList() {
        listArray = new Object[CONFIRMED_SIZE];
    }

    private void grow() {
        int oldCapacity = listArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 1.5x
        resizeTo(newCapacity);
    }

    private void resizeTo(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(listArray, 0, newArray, 0, size);
        listArray = newArray;
    }

    private boolean validateElementIndexInclude0(int index) {
        return index >= 0 && index <= size;
    }

    private boolean validateElementIndexExclude0(int index) {
        return index >= 0 && index < size;
    }

    private void capacityIsFull() {
        if (size == listArray.length) {
            grow();
        }
    }

    @Override
    public void add(T value) {
        capacityIsFull();
        listArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (validateElementIndexInclude0(index)) {
            capacityIsFull();
            int numMoved = size - index;
            if (numMoved > 0) {
                System.arraycopy(listArray, index, listArray, index + 1, numMoved);
            }
            listArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (validateElementIndexExclude0(index)) {
            return (T) listArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void set(T value, int index) {
        if (validateElementIndexExclude0(index)) {
            listArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(int index) {
        if (validateElementIndexExclude0(index)) {
            final T tempDel = (T) listArray[index];
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(listArray, index + 1, listArray, index, numMoved);
            }
            listArray[--size] = null;
            return tempDel;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(T element) {
        int elementIndex = -1;
        for (int i = 0; i < size; i++) {
            if (listArray[i] == null ? listArray[i] == element : listArray[i].equals(element)) {
                elementIndex = i;
                break;
            }
        }
        if (elementIndex >= 0 && elementIndex < size) {
            final T tempDel = (T) listArray[elementIndex];
            int elementsToMove = size - elementIndex - 1;
            if (elementsToMove > 0) {
                System.arraycopy(listArray, elementIndex + 1,
                        listArray, elementIndex, elementsToMove);
            }
            listArray[--size] = null;
            return tempDel;
        } else {
            throw new NoSuchElementException("Element not found: " + element);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
}
