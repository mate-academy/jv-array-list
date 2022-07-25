package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_INCREASE_FACTOR = 1.5;
    private Object[] elementData; // should we use transient keyword?
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = buildUp();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == elementData.length) {
            elementData = buildUp();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkList(list);
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        checkIndexEqualsSize(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        checkIndexEqualsSize(index);
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        checkIndexEqualsSize(index);
        final T oldValue = (T) elementData[index];
        if (size - 1 > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        }
        elementData[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = getElement(element);
        if (index == -1) {
            throw new NoSuchElementException("This element does not exist: " + element);
        }
        remove(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] buildUp() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * SIZE_INCREASE_FACTOR);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, oldCapacity);
        return newElementData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private void checkIndexEqualsSize(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private void checkList(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List : " + list + " can't be null");
        }
    }

    @SuppressWarnings("unchecked")
    private int getElement(T element) {
        for (int i = 0; i < size; i++) {
            T value = (T) elementData[i];
            if (value != null && value.equals(element)
                    || value == element) {
                return i;
            }
        }
        return -1;
    }
}
