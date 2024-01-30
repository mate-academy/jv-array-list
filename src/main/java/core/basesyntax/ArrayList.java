package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            increaseCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(indexOutOfBoundsMsg(index));
        }
        if (!(size < elementData.length)) {
            increaseCapacity();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    private void increaseCapacity() {
        int newCapacity = size + (size >> 1);
        Object[] oldElementData = elementData;
        elementData = new Object[newCapacity];
        System.arraycopy(oldElementData, 0, elementData, 0, oldElementData.length);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = (T) elementData[index];
        int numberOfElementsToMove = size - index - 1;
        if (numberOfElementsToMove > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numberOfElementsToMove);
        }
        size--;
        elementData[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Can't remove element " + element + " from the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(indexOutOfBoundsMsg(index));
        }
    }

    private String indexOutOfBoundsMsg(int index) {
        return "Illegal index: " + index + ", for the list size: " + size;
    }
}
