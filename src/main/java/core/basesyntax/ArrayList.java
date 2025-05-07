package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int BIT_SHIFT = 1;
    private static final int LIST_ELEMENTS_FIRST_INDEX = 0;

    private T[] listElements;
    private int size = 0;

    public ArrayList() {
        listElements = (T[]) new Object[DEFAULT_CAPACITY];
    }
 
    @Override
    public void add(T value) {
        ensureCapacity(size + 1);

        this.listElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size + 1);

        checkIndexAccordingToSize(index, size + 1);

        System.arraycopy(listElements, index, listElements, index + 1, size - index);

        this.listElements[index] = value;

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
        checkIndexAccordingToSize(index, size);

        return listElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexAccordingToSize(index, size);

        this.listElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexAccordingToSize(index, size);

        T removedElement = listElements[index];

        System.arraycopy(listElements, index + 1, listElements, index, size - index - 1);

        this.listElements[--size] = null;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        int findedIndex = -1;

        for (int i = 0; i < size; i++) {
            if (listElements[i] != null ? listElements[i].equals(element) : element == null) {
                findedIndex = i;
            }
        }

        if (findedIndex == -1) {
            throw new NoSuchElementException("No such element: " + element.toString());
        }

        return remove(findedIndex);
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
        if (minCapacity > listElements.length) {
            this.listElements = grow();
        }
    }

    private T[] grow() {
        int oldCapacity = listElements.length;
        int newCapacity = oldCapacity + (oldCapacity >> BIT_SHIFT);

        T[] increasedListElements = (T[]) new Object[newCapacity];

        System.arraycopy(
                listElements,
                LIST_ELEMENTS_FIRST_INDEX, 
                increasedListElements, 
                LIST_ELEMENTS_FIRST_INDEX, 
                oldCapacity
        );

        return increasedListElements;
    }

    private void checkIndexAccordingToSize(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
