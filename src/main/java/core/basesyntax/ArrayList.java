package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int size = 0;
    private final int defaultSize = 10;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[defaultSize];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexExeption(index);
        resize();
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
        indexExeption(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexExeption(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExeption(index);
        final T removedObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element) || element == elements[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array");
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
        if (elements.length == size) {
            T[] increasedObjectsArray = (T[]) new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, increasedObjectsArray, 0, elements.length);
            elements = increasedObjectsArray;
        }
    }

    private void indexExeption(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }
}
