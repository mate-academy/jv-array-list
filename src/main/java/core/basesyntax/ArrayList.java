package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NO_SUCH_ELEMENT_INDEX = -1;
    private static final double MAX_CAPACITY_RAISE_INDEX = 1.5;

    private T[] elementData;
    private T[] rewriteElementData;
    private int size = 0;
    private int currentMaxCapacity = DEFAULT_CAPACITY;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resizeRewriteElementData(int newSize) {
        rewriteElementData = (T[]) new Object[newSize];
    }

    private void resizeElementData(int newSize) {
        elementData = (T[]) new Object[newSize];
    }

    private boolean correctIndexCheck(int index) {
        if (index > size - 1 || index < 0) {
            return false;
        }
        return true;
    }

    @Override
    public void add(T value) {
        if (size + 1 <= currentMaxCapacity) {
            elementData[size] = value;
        } else {
            currentMaxCapacity *= MAX_CAPACITY_RAISE_INDEX;
            resizeRewriteElementData(size + 1);
            System.arraycopy(elementData, 0, rewriteElementData, 0, size);
            rewriteElementData[size] = value;
            resizeElementData(currentMaxCapacity);
            System.arraycopy(rewriteElementData, 0, elementData, 0, rewriteElementData.length);
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Array List shorter then you think");
        }
        resizeRewriteElementData(size + 1);
        if (size + 1 <= currentMaxCapacity) {
            System.arraycopy(elementData, 0, rewriteElementData, 0, index);
            rewriteElementData[index] = value;
            System.arraycopy(elementData, index, rewriteElementData, index + 1, size - index);
        } else {
            currentMaxCapacity *= MAX_CAPACITY_RAISE_INDEX;
            System.arraycopy(elementData, 0, rewriteElementData, 0, index);
            rewriteElementData[index] = value;
            System.arraycopy(elementData, index, rewriteElementData, index + 1, size - index);
            resizeElementData(currentMaxCapacity);
        }
        System.arraycopy(rewriteElementData, 0, elementData, 0, rewriteElementData.length);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() < currentMaxCapacity) {
            for (int i = size; i < (size + list.size()); i++) {
                elementData[i] = list.get(i - size);
            }
        } else {
            do {
                currentMaxCapacity *= MAX_CAPACITY_RAISE_INDEX;
            } while (size + list.size() >= currentMaxCapacity);
            resizeRewriteElementData(size + list.size());
            System.arraycopy(elementData, 0, rewriteElementData, 0, size);
            for (int i = size; i < size + list.size(); i++) {
                rewriteElementData[i] = list.get(i - size);
            }
            resizeElementData(currentMaxCapacity);
            System.arraycopy(rewriteElementData, 0, elementData, 0, rewriteElementData.length);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (correctIndexCheck(index) == false) {
            throw new ArrayListIndexOutOfBoundsException("Array List shorter then you think");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (correctIndexCheck(index) == false) {
            throw new ArrayListIndexOutOfBoundsException("Array List shorter then you think");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedObject;
        if (correctIndexCheck(index) == false) {
            throw new ArrayListIndexOutOfBoundsException("Array List shorter then you think");
        }
        removedObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        elementData[size - 1] = null;
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        int index = NO_SUCH_ELEMENT_INDEX;
        for (int i = 0; i < size; i++) {
            if ((elementData[i] != null && elementData[i].equals(element))
                    || (elementData[i] == null && element == null)) {
                index = i;
                break;
            }
        }
        if (index == NO_SUCH_ELEMENT_INDEX) {
            throw new NoSuchElementException("NoSuchElementException");
        }
        return remove(index);
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
