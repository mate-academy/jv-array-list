package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_INDEX = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            arrayIncrease();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size + 1);
        if (size == elements.length) {
            arrayIncrease();
        }
        checkIndexBounds(index);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length + (elements.length >> 1), minCapacity);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        elements[index] = value;
    }

    private void arrayIncrease() {
        int newCapacity = (int) (elements.length * GROWTH_INDEX);
        ensureCapacity(newCapacity);
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        final T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                final T removedElement = (T) elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null;
                size--;
                return removedElement;
            }
        }
        throw new java.util.NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
