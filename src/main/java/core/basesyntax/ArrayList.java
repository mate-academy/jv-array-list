package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkIncreaseArray();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            checkIncreaseArray();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        return remove(indexOf(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        int newSize = elements.length + (elements.length >> 1);
        elements = Arrays.copyOf(elements, newSize);
        System.arraycopy(elements, 0, elements, 0, size);
    }

    private void checkIncreaseArray() {
        if (elements.length == size) {
            resizeArray();
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(String.format("Index '%s' is invalid",
                    index));
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException(String.format("Element: '%s' not found", element));
    }
}
