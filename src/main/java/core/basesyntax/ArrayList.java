package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int startCapacity) {
        checkStartCapacity(startCapacity);
        elementData = (T[]) new Object[startCapacity];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T elementRemoved = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return elementRemoved;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null ? elementData[i] == element
                    : elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;

            if (elementData.length > 0) {
                int newCapacity = oldCapacity + (oldCapacity / 2);
                T[] buffedElementData = elementData;
                elementData = (T[]) new Object[newCapacity];
                System.arraycopy(buffedElementData, 0,
                        elementData, 0, oldCapacity);
            }
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkStartCapacity(int startCapacity) {
        if (startCapacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Start capacity should be > 0");
        }
    }
}
