package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_EXCEPTION_MESSAGE = "We haven't element at index ";
    private static final String AVAILABILITY_EXCEPTION_MESSAGE = "We don't have element: ";
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private Object[] elements;

    public ArrayList() {
        this.size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elements.length <= size + 1) {
            elements = Arrays.copyOf(elements, (int) (elements.length * 1.5));
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckFotInsert(index);
        if (elements.length <= size + 1) {
            elements = Arrays.copyOf(elements, (int) (elements.length * 1.5));
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
        indexCheckFoUpdDel(index);
        return elements(index);
    }

    @Override
    public void set(T value, int index) {
        indexCheckFoUpdDel(index);
        for (int i = 0; i < size; i++) {
            if (i == index) {
                elements[i] = value;
            }
        }
    }

    @Override
    public T remove(int index) {
        indexCheckFoUpdDel(index);
        T oldValue = elements(index);
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(AVAILABILITY_EXCEPTION_MESSAGE + element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T elements(int index) {
        return (T) elements[index];
    }

    private void indexCheckFotInsert(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE + index);
        }
    }

    private void indexCheckFoUpdDel(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE + index);
        }
    }
}
