package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int elementPosition = 0;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {

        if (elementPosition == elementData.length) {
            resize();
        }
        elementData[elementPosition] = value;
        elementPosition++;

    }

    @Override
    public void add(T value, int index) {
        if (index > elementPosition || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bound");
        }
        if (elementPosition == elementData.length) {
            resize();
        }
        System.arraycopy(elementData, index, elementData, index + 1, elementPosition - index);
        elementData[index] = value;
        elementPosition++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= elementPosition || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bound");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= elementPosition || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bound");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= elementPosition) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        Object elementToRemove = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, elementPosition - 1 - index);
        elementPosition--;
        return (T) elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementPosition; i++) {
            if (elementData[i] == element || (elementData[i] != null
                    && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElementException");
    }

    @Override
    public int size() {
        return elementPosition;
    }

    @Override
    public boolean isEmpty() {
        return elementPosition == 0;
    }

    private void resize() {
        int newCapacity = (int) (elementData.length * 1.5);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementPosition);
        elementData = (T[]) newElementData;
    }
}
