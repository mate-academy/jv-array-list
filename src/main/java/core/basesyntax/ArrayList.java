package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ONE_ELEMENT = 1;
    private static final double SIZE_GROWTH_INDEX = 1.5;

    private Object[] elementData;
    private int capacity = DEFAULT_CAPACITY;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity >= 0 && initialCapacity < capacity) {
            this.elementData = new Object[capacity];
        } else if (initialCapacity > capacity) {
            this.elementData = new Object[capacity];
            this.elementData = grow();
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = new Object[capacity];
    }

    @Override
    public void add(T value) {
        int newLength = size + ONE_ELEMENT;
        if (newLength > elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index provided");
        }
        if (index > size && index < size + ONE_ELEMENT) {
            size = index;
        } else if (index > size + ONE_ELEMENT) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index provided");
        }
        int newLength = size + ONE_ELEMENT;
        if (newLength >= elementData.length || index > capacity) {
            elementData = grow();
        }
        if (index != size + ONE_ELEMENT) {
            System.arraycopy(elementData, index, elementData,
                    index + ONE_ELEMENT, newLength - index);
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newLength = size + list.size();
        if (newLength >= elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        final Object temp = elementData[index];
        if (index != size - ONE_ELEMENT) {
            System.arraycopy(elementData, index + ONE_ELEMENT, elementData, index, size - index);
        }
        elementData[size - ONE_ELEMENT] = null;
        size--;
        return (T) temp;
    }

    @Override
    public T remove(T element) {
        int index = checkIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the array");
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

    private Object[] grow() {
        capacity = (int) (capacity * SIZE_GROWTH_INDEX);
        Object[] increasedArray = new Object[capacity];
        System.arraycopy(elementData,0, increasedArray, 0,elementData.length);
        return increasedArray;
    }

    private int checkIndex(T value) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == value
                    || (elementData[i] != null && elementData[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index provided");
        }
    }
}
