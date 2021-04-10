package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int CAPASITY = 10;
    private static final int ELEMENT_iS_MISSING = -1;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[CAPASITY];
    }

    @Override
    public void add(T value) {
        if (size + 1 > elementData.length) {
            ensureCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size || goodIndex(index)) { // index >= 0,  index <= size !!!!
            if (size + 1 > elementData.length) {
                ensureCapacity();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > elementData.length) {
            ensureCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        goodIndex(index); // index >= 0,  index < size
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        goodIndex(index); // index >= 0,  index < size
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        goodIndex(index); // index >= 0  index < size
        int numMoved = size - index - 1; // скільки елементів треба скопіювати
        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int indexElementForRemove = ELEMENT_iS_MISSING;
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                indexElementForRemove = i;
                break;
            }
        }
        if (indexElementForRemove == ELEMENT_iS_MISSING) {
            throw new NoSuchElementException();
        }
        return remove(indexElementForRemove);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        int newCapacity = (elementData.length * 3 / 2) + 1;
        T[] newElementData = (T[])new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private boolean goodIndex(int index) {
        if (index < size && index >= 0) {
            return true;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
    }
}
