package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ENSURE_CAPACITY_DIVIDER = 2;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexRangeInAdd(index);
        checkCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkCapacity();
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elements[i] == null) {
                    removedElement = remove(i);
                    return removedElement;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.elements[i])) {
                    removedElement = remove(i);
                }
            }
            if (removedElement == null) {
                throw new NoSuchElementException("No such element: " + element + "in list");
            }
        }
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size == elements.length) {
            ensureCapacity();
        }
    }

    private void ensureCapacity() {
        int newCapacity = (elements.length / ENSURE_CAPACITY_DIVIDER) + elements.length;
        T[] copyElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, copyElements, 0, elements.length);
        elements = copyElements;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                                                                 + index
                                                                 + "out of bounds "
                                                                 + "for length: "
                                                                 + elements.length);
        }
    }

    private void checkIndexRangeInAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                                                                 + index
                                                                 + "out of bounds "
                                                                 + "for length: "
                                                                 + elements.length);
        }
    }
}
