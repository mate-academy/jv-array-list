package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Capacity: " + initialCapacity);
        }
        elements = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int position) {
        if (position < 0 || position > size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Index: " + position);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, position, elements, position + 1, size - position);
        elements[position] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int addedListSize = list.size();
        T[] arrayForAddedListElements = (T[]) new Object[addedListSize];
        for (int i = 0; i < addedListSize; i++) {
            arrayForAddedListElements[i] = list.get(i);
        }
        System.arraycopy(arrayForAddedListElements, 0, elements, size, addedListSize);
        size += addedListSize;
    }

    @Override
    public T get(int indexPosition) {
        isIndexExist(indexPosition);
        return elements[indexPosition];
    }

    @Override
    public void set(T value, int indexPosition) {
        isIndexExist(indexPosition);
        elements[indexPosition] = value;
    }

    @Override
    public T remove(int indexPosition) {
        isIndexExist(indexPosition);
        T removedElement = elements[indexPosition];
        int numMoved = size - indexPosition - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, indexPosition + 1, elements, indexPosition, numMoved);
        }
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int indexPosition = indexOf(element);
        if (indexPosition == -1) {
            throw new java.util.NoSuchElementException("Element not found in ArrayList");
        } else {
            remove(indexPosition);
        }
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

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity > Integer.MAX_VALUE - 8) {
            throw new OutOfMemoryError();
        }
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, oldCapacity);
        elements = newArray;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null) {
                return i;
            }
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void isIndexExist(int indexPosition) {
        if (indexPosition < 0 || indexPosition >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Index: " + indexPosition);
        }
    }
}

