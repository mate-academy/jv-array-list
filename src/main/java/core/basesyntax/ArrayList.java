package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double HALF = 0.5;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList(int size) {
        if (size > 0) {
            this.elementData = new Object[size];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + size);
        }
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "size " + size);
        }
        indexInRange(index, elementData.length - 1);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        indexInRange(index, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexInRange(index, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexInRange(index, size - 1);
        final T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                elementData.length - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("there is no such element " + element);
        }
        T oldValue = (T) elementData[index];
        remove(index);
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexInRange(int index, int end) {
        if (index < 0 || index > end) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "size " + size);
        }
    }

    private void grow() {
        int newSize = (int) (elementData.length + (elementData.length * HALF));
        grow(newSize);
    }

    private void grow(int count) {
        int newSize = elementData.length + count;
        Object[] tmp = new Object[newSize];
        System.arraycopy(elementData, 0, tmp, 0, elementData.length);
        elementData = tmp;
    }
}
