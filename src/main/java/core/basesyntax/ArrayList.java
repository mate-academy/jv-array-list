package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    @SuppressWarnings("unchecked")
    private T[] arrayData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        arrayData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        arrayData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, "get");
        return arrayData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, "set");
        arrayData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, "remove");
        T removed = arrayData[index];
        System.arraycopy(arrayData, index + 1, arrayData, index, size - index - 1);
        arrayData[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && arrayData[i] == null) 
                    || (element != null && element.equals(arrayData[i]))) {
                return remove(i);
            }
        }
        throw new java.util.NoSuchElementException("Element not found: " + element);
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
        if (minCapacity <= arrayData.length) {
            return;
        }
        int newCapacity = arrayData.length;
        while (newCapacity < minCapacity) {
            newCapacity = (int) (newCapacity * GROWTH_FACTOR);
        }
        arrayData = java.util.Arrays.copyOf(arrayData, newCapacity);
    }

    private void checkIndex(int index, String operation) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                "Cannot " + operation + " at index " + index + "; size is " + size
            );
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                "Cannot add at index " + index + "; size is " + size
            );
        }
    }
}
