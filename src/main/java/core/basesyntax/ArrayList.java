package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_INCREASE_FACTOR = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
        if (size == elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkList(list);
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        final T oldValue = (T) elements[index];
        if (size - 1 > index) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = getElement(element);
        if (index == -1) {
            throw new NoSuchElementException("This element does not exist: " + element);
        }
        remove(index);
        return element;
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
        int oldCapacity = elements.length;
        int newCapacity = (int) (oldCapacity * SIZE_INCREASE_FACTOR);
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, oldCapacity);
        elements = newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private void checkList(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List : " + list + " can't be null");
        }
    }

    @SuppressWarnings("unchecked")
    private int getElement(T element) {
        for (int i = 0; i < size; i++) {
            T value = (T) elements[i];
            if (value != null && value.equals(element)
                    || value == element) {
                return i;
            }
        }
        return -1;
    }
}
