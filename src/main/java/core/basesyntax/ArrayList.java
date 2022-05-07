package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int ITEMS_NUMBER = 16;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        elements = (T[]) new Object[ITEMS_NUMBER];
    }

    public void resize() {
        if (elements.length <= size || size >= ITEMS_NUMBER) {
            elements = Arrays.copyOf(elements, size * 3 / 2);
        }
    }

    @Override
    public void add(T value) {
        resize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            resize();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("The index is negative");
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
        if (index >= 0 && index < size) {
            return elements[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index don't exist");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index is negative or greater than length");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index is negative or greater than length");
        } else {
            T term = elements[index];
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            size--;
            return term;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There isn't present element");
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
