package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ifFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        ifFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        T removed = elements[index];
        int elementsMove = size - index - 1;
        if (elementsMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsMove);
        }
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                if (element == null) {
                    return remove(i);
                }
            } else if (elements[i].equals(element)) {
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

    private void resize() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index "
                            + index
                            + " out of bounds for size "
                            + size
            );
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index "
                            + index
                            + " is invalid for insertion; size "
                            + size
            );

        }
    }

    private void ifFull() {
        if (size == elements.length) {
            resize();
        }
    }
}
