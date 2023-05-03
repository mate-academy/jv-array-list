package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NUM_ONE = 1;
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkGrowIfNeed();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddByIndex(index);
        checkGrowIfNeed();
        arrayCopyForAddByIndex(index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (elementData.length > list.size()) {
            for (int i = 0; i < list.size(); i++) {
                elementData[size++] = list.get(i);
            }
        } else {
            grow();
            addAll(list);
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
        T oldValue = elementData[index];
        removeByIndex(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < elementData.length; index++) {
            if (equalsElemets(elementData[index], element)) {
                removeByIndex(elementData, index);
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

    private void checkGrowIfNeed() {
        if (size >= DEFAULT_SIZE) {
            grow();
        }
    }

    private T[] grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != null) {
            int newCapacity = oldCapacity + (DEFAULT_SIZE >> NUM_ONE) + NUM_ONE;
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            throw new ArrayListIndexOutOfBoundsException("Error in grow method");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index >= size or index < 0");
        }
    }

    private void checkIndexForAddByIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index > size or index < 0");
        }
    }

    private void arrayCopyForAddByIndex(int index) {
        System.arraycopy(elementData, index, elementData, index + NUM_ONE, size - index);
    }

    private void removeByIndex(T[] elementData, int index) {
        System.arraycopy(elementData, index + NUM_ONE, elementData, index, size - NUM_ONE - index);
        size--;
    }

    private boolean equalsElemets(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
