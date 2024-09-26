package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void growIfArrayIsFull() {
        if (size == elements.length) {
            Object[] newArray = new Object[Math.round(elements.length * 1.5f)];
            for (int i = 0; i < elements.length; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
        }
    }

    @Override
    public void add(T value) {
        growIfArrayIsFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index didn't exist");
        }
        if (elements[index] == null) {
            add(value);
        } else {
            growIfArrayIsFull();
            size++;
            for (int i = size - 1; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index didn't exist");
        } else {
            return (T) elements[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index didn't exist");
        } else {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index didn't exist");
        } else {
            final Object element = elements[index];
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
            size--;
            return (T) element;
        }
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    for (int j = i; j < size; j++) {
                        elements[j] = elements[j + 1];
                    }
                    size--;
                    return element;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                for (int j = i; j < size; j++) {
                    elements[j] = elements[j + 1];
                }
                size--;
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size != 0) {
            return false;
        } else {
            return true;
        }
    }
}
