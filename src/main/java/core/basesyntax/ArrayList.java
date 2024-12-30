package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double RESIZE_FACTOR = 1.5;

    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == this.elements.length) {
            this.resize((int) (elements.length * RESIZE_FACTOR));
        }
    }

    private void resize(int newCapacity) {
        T[] elements = (T[]) new Object[newCapacity];
        System.arraycopy(this.elements, 0, elements, 0, size);
        this.elements = elements;
    }

    private void indexAssert(int index, String context) {
        switch (context) {
            case "index":
                if (index < 0 || index >= size) {
                    throw new ArrayListIndexOutOfBoundsException("Index out of bounds.",
                            new IndexOutOfBoundsException(index));
                }
                break;
            case "size":
                if (index < 0 || index > size) {
                    throw new ArrayListIndexOutOfBoundsException("Size out of bounds.",
                            new IndexOutOfBoundsException(index));
                }
                break;
            default:
                System.out.println("No such context defined: acceptable {index, size}");
        }
    }

    @Override
    public void add(T value) {
        this.ensureCapacity();
        this.elements[size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        this.indexAssert(index, "size");
        this.ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        this.elements[index] = value;
        this.size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        this.indexAssert(index, "index");
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        this.indexAssert(index, "index");
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        this.indexAssert(index, "index");
        T swap = elements[index];
        System.arraycopy(this.elements, index + 1, this.elements, index, size - index - 1);
        size--;
        return swap;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element != null) {
                continue;
            }
            if (element == null && elements[i] == null) {
                this.remove(i);
                return null;
            }
            if (elements[i].equals(element)) {
                this.remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element for removing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
