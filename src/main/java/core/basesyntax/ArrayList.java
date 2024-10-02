package core.basesyntax;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * 1.5 + 1);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    @Override
    public boolean add(T element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " to go beyond the list");
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " to go beyond the list");
        }
        return (T) elements[index];
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " to go beyond the list");
        }
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1,
                elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            // Якщо обидва значення null або якщо елементи рівні
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                remove(i);
                return true;
            }
        }
        throw new NoSuchElementException(
                "Element not found.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " to go beyond the list");
        }
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(java.util.Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, java.util.Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        throw new UnsupportedOperationException();
    }
}
