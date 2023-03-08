package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) (new Object[DEFAULT_SIZE]);
    }

    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size >= elements.length) {
            elements = getExpanded();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can't add an element at that index");
        } else if (index == size) {
            elements[index] = value;
        } else {
            elements = insertElement(value, index);
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no element with such index");
        } else {
            return elements[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (get(index) != null) {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        elements = removeAndCopy(index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        return removeAndCopy(element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] getExpanded() {
        T[] expandedArray = (T[]) (new Object[elements.length * 2]);
        System.arraycopy(elements, 0, expandedArray, 0, elements.length);
        return expandedArray;
    }

    private T[] insertElement(T value, int index) {
        T[] copyWithInsertion = (T[]) (new Object[elements.length + 1]);
        for (int i = 0; i <= size; i++) {
            if (i == index) {
                copyWithInsertion[i] = value;
            } else if (i > index) {
                copyWithInsertion[i] = elements[i - 1];
            } else {
                copyWithInsertion[i] = elements[i];
            }
        }
        return copyWithInsertion;
    }

    private T[] removeAndCopy(int index) {
        T[] copyWithRemoval = (T[]) (new Object[elements.length]);
        for (int i = 0; i < size; i++) {
            if (i > index) {
                copyWithRemoval[i - 1] = elements[i];
            } else if (i < index) {
                copyWithRemoval[i] = elements[i];
            }
        }
        return copyWithRemoval;
    }

    private T removeAndCopy(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("you tried to remove a non-existent element");
    }
}
