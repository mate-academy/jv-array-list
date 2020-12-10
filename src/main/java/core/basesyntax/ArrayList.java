package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    
    private T[] elements;
    private int size;
    
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    @Override
    public void add(T value) {
        add(value, size);
    }
    
    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size++ - index);
        elements[index] = value;
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
        checkIndex(index);
        T oldElement = get(index);
        System.arraycopy(elements, index + 1, elements, index, --size - index);
        return oldElement;
    }
    
    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == t || (elements[i] != null && elements[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element \"" + t + "\" not found.");
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void grow() {
        elements = Arrays.copyOf(elements, (int) (elements.length * 1.5));
    }
    
    private void checkAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
