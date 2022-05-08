package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements = new Object[DEFAULT_ARRAY_SIZE];
    private int size;

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void resize() {
        if (elements.length == size) {
            Object[] dummyList = new Object[(int) (size * 1.5)];
            System.arraycopy(elements, 0, dummyList, 0, elements.length);
            elements = dummyList;
        }
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        resize();
        System.arraycopy(elements, index, elements, index + 1, elements.length - index - 1);
        resize();
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            ArrayList.this.add(list.get(i));
        }
    }

    @Override
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
    public T remove(int index) {
        checkIndex(index);
        if (index == size - 1) {
            T result = (T) elements[index];
            elements[index] = null;
            size--;
            return result;
        }
        T result = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T currentObject = (T) elements[i];
            if (currentObject == element
                    || (currentObject != null
                    && element != null
                    && currentObject.equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element is not in the List");
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
