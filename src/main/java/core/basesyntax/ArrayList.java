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
        checkIndex(index);
        if (index >= elements.length) {
            grow();
            add(value, index);
        } else if (index < size) {
            if (size + 1 > elements.length) {
                grow();
            }
            T[] firstSubArray = Arrays.copyOfRange(elements, 0, index + 1);
            T[] secondSubArray = Arrays.copyOfRange(elements, index, elements.length - 1);
            firstSubArray[firstSubArray.length - 1] = value;
            T[] resultArray = Arrays.copyOf(firstSubArray,
                    firstSubArray.length + secondSubArray.length);
            System.arraycopy(secondSubArray, 0, resultArray,
                    firstSubArray.length, secondSubArray.length);
            elements = resultArray;
            size++;
        } else {
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
        checkGetIndex(index);
        return elements[index];
    }
    
    @Override
    public void set(T value, int index) {
        checkGetIndex(index);
        elements[index] = value;
    }
    
    @Override
    public T remove(int index) {
        checkIndex(index);
        T[] firstSubArray = Arrays.copyOfRange(elements, 0, index);
        T[] secondSubArray = Arrays.copyOfRange(elements, index + 1, elements.length);
        T[] resultArray = Arrays.copyOf(firstSubArray,
                firstSubArray.length + secondSubArray.length + 1);
        System.arraycopy(secondSubArray, 0, resultArray,
                firstSubArray.length, secondSubArray.length);
        T oldElement = get(index);
        elements = resultArray;
        size--;
        return oldElement;
    }
    
    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == t || (elements[i] != null && elements[i].equals(t))) {
                T removedElement = get(i);
                remove(i);
                return removedElement;
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
    
    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    private void checkGetIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
