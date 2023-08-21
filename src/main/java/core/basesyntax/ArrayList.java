package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_EXCEPTION_MESSAGE = "Element missing for given index ";
    private static final String AVAILABILITY_EXCEPTION_MESSAGE = "Element missing: ";
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        this.size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkArraySize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckFotInsert(index);
        checkArraySize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheckFoUpdDel(index);
        T oldValue = elements(index);
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
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

    private void checkArraySize() {
        if (elements.length <= size + 1) {
            Object[] newArray = new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }
}
