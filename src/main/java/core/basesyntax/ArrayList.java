package core.basesyntax;

import java.util.Objects;
import java.util.NoSuchElementException;


public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void resize() {
        int newCapacity = elements.length + elements.length / 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public void add(T value) {
        if(size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == elements.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }


    @Override
    public void addAll(List<T> list) {
        if (list == null || list.size() == 0) {
            return; // нічого не додаємо, якщо список порожній
        }

        int newElementsCount = list.size();
        if (size + newElementsCount > elements.length) {
            resize();
        }

        for (int i = 0; i < newElementsCount; i++) {
            elements[size] = list.get(i);
            size++;
        }
    }


    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null; // очищаємо останній елемент
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
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
