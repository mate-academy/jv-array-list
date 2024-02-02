package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private final int defaultSize;
    private final double resizeFactor;
    private int size;
    private T[] elements;

    public ArrayList() {
        defaultSize = 10;
        resizeFactor = 0.5;
        elements = (T[]) new Object[defaultSize];
    }

    @Override
    public void add(T value) {
        growArray();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The size of the list less than your index");
        }
        growArray();
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
        exceptionCalling(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        exceptionCalling(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        exceptionCalling(index);
        T oldValue = elements[index];
        int numMoved = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if ((Objects.equals(elements[i], null) && Objects.equals(element, null))
                    || Objects.equals(element, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray() {
        int currentCapacity = elements.length;
        if (size >= currentCapacity) {
            int newCapacity = currentCapacity + (int) (currentCapacity * resizeFactor);
            T[] array = (T[])new Object[newCapacity];
            System.arraycopy(elements, 0, array, 0, currentCapacity);
            elements = array;
        }
    }

    private void exceptionCalling(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
