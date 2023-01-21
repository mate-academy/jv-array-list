package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList(int initialCapacity) {
        if(initialCapacity <= 0) {
            throw new IllegalArgumentException();
        } else {
            elements = new Object[initialCapacity];
        }
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public void resizeIdIndex() {
        if (elements.length == size) {
            Object[] newElements = new Object[elements.length * 3 / 2 + 1];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public void add(T value) {
        resizeIdIndex();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index is negative or index is bigger than size");
        }
        Objects.checkIndex(index, size + 1);
        resizeIdIndex();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        IntStream.range(0, list.size())
                .mapToObj(list::get)
                .forEach(this::add);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't get index,because index is negative or index is bigger than size");
        }
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't set index, because index is negative or index is bigger than size");
        }
        Objects.checkIndex(index, size);
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove index, because index is negative or index is bigger than size");
        }
        Objects.checkIndex(index, size);
        T removedElements = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElements;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
