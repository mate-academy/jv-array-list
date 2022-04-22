package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY_OF_ELEMENT_DATA = 10;
    private T[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[CAPACITY_OF_ELEMENT_DATA];
    }

    @Override
    public void add(T value) {
        increaseSizeArray();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        increaseSizeArray();
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Yuo can't add "
                    + "element by this index");
        }
        System.arraycopy(elementData, index, elementData,
                index + 1, size - index);
        elementData[index] = value;
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
        return elementData[checkIndex(index)];
    }

    @Override
    public void set(T value, int index) {
        elementData[checkIndex(index)] = value;
    }

    @Override
    public T remove(int index) {
        T temp = null;
        temp = elementData[checkIndex(index)];
        elementData[index] = null;
        moveElementsAfterRemove(index);
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((element == null && elementData[i] == null)
                    || (elementData[i] != null
                    && elementData[i].equals(element))) {
                moveElementsAfterRemove(i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("You can't use this "
                    + "index");
        }
        return index;
    }

    private void increaseSizeArray() {
        if (size >= elementData.length) {
            T[] temp = (T[]) new Object[elementData.length
                    + elementData.length / 2];
            System.arraycopy(elementData, 0, temp, 0,
                    elementData.length);
            elementData = temp;
        }
    }

    private void moveElementsAfterRemove(int index) {
        if (elementData.length - 1 - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    elementData.length - 1 - index);
        }
    }
}
