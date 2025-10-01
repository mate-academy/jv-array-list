package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR_NUMERATOR = 3;
    private static final int GROWTH_FACTOR_DENOMINATOR = 2;

    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list == this) {
            int n = size;
            ensureCapacity(size + n);
            for (int i = 0; i < n; i++) {
                elementData[size++] = get(i);
            }
            return;
        }

        int n = list.size();
        ensureCapacity(size + n);
        for (int i = 0; i < n; i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkElementIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkElementIndex(index);
        T old = (T) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; // help GC
        return old;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            Object current = elementData[i];
            if (element == null ? current == null : element.equals(current)) {
                T old = (T) current;
                int numMoved = size - i - 1;
                if (numMoved > 0) {
                    System.arraycopy(elementData, i + 1, elementData, i, numMoved);
                }
                elementData[--size] = null;
                return old;
            }
        }
        throw new NoSuchElementException("No such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCap = elementData.length;
        if (minCapacity > oldCap) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCap = elementData.length;
        int newCap = (int) (((long) oldCap * GROWTH_FACTOR_NUMERATOR) / GROWTH_FACTOR_DENOMINATOR);
        if (newCap < minCapacity) {
            newCap = minCapacity;
        }
        Object[] newArr = new Object[newCap];
        System.arraycopy(elementData, 0, newArr, 0, size);
        elementData = newArr;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
