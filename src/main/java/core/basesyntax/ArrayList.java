package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;
    private T[] elements = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        ensureCapacity(1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex(index)) {
            ensureCapacity(1);
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return elements[index];
        }
        throw new ArrayIndexOutOfBoundsException("Invalid index!");
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            T result = elements[index];
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            size--;
            return result;
        }
        throw new ArrayIndexOutOfBoundsException("Invalid index!");
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException("No such value found!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int numberOfElementsToAdd) {
        if (size + numberOfElementsToAdd >= elements.length) {
            T[] newElements = (T[]) new Object[elements.length
                    + elements.length / 2 + numberOfElementsToAdd];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    private boolean checkIndex(int index) {
        if (index < size && index >= 0) {
            return true;
        }
        throw new ArrayIndexOutOfBoundsException("source index " + index
                + " is out of bounds for maximum index " + (size - 1));
    }
}
