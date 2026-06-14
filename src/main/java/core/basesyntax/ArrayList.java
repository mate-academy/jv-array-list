package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROWTH_FACTOR = 1.5f;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        Object[] newArray = new Object[(int) (elements.length * GROWTH_FACTOR)];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            for (int i = 0; i < elements.length; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == elements.length) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            for (int i = 0; i < elements.length; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
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
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        elements[index] = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T oldValue = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    final T oldValue = (T) elements[i];
                    for (int k = i; k < size - 1; k++) {
                        elements[k] = elements[k + 1];
                    }
                    elements[size - 1] = null;
                    size--;
                    return oldValue;
                }
            } else if (elements[i] != null && elements[i].equals(element)) {
                final T oldValue = (T) elements[i];
                for (int k = i; k < size - 1; k++) {
                    elements[k] = elements[k + 1];
                }
                elements[size - 1] = null;
                size--;
                return (T) oldValue;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found");
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
