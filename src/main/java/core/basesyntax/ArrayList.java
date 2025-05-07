package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexRangeInAdd(index);
        resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
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
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element + " in list");
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
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * CAPACITY_INDEX);
            T[] copyElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, copyElements, 0, elements.length);
            elements = copyElements;
        }
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
