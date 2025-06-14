package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    
    public static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    
    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }
    
    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index);
        }
        ensureCapacity();
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
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }
    
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removed;
    }
    
    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    return remove(i);
                }
            } else {
                if (element.equals(elements[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length + elements.length / 2;
            Object[] newElements = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }
    
    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index);
        }
    }
}
