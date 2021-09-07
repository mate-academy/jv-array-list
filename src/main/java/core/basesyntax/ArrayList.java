package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int capacity = 10;
    private int elementPosition = 0;
    private Object[] elementData = new Object[capacity];

    @Override
    public void add(T value) {

        if (elementPosition == elementData.length) {
            int newCapacity = (int) (elementData.length * 1.5);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementPosition);
            elementData = newElementData;
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
            int newCapacity = (int) (elementData.length * 1.5);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementPosition);
            elementData = newElementData;
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

        Object elementRemove = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, elementPosition - 1);
        elementPosition--;
        return (T) elementRemove;
    }

    @Override
    public T remove(T element) {
        int indexRemove = 0;
        for (int i = 0; i < elementData.length; i++) {
            if (element != null && element.equals(elementData[i])) {
                indexRemove = i;
            } else {
                throw new NoSuchElementException();
            }
        }
        return (T) remove(indexRemove);
    }

    @Override
    public int size() {
        return elementPosition;
    }

    @Override
    public boolean isEmpty() {
        return elementPosition == 0;
    }
}
