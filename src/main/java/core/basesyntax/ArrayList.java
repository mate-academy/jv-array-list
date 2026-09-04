package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] data;
    
    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }
    
    @Override
    public void add(T value) {
        if (size >= data.length) {
            growData();
        }
        
        data[size] = value;
        size++;
    }
    
    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        
        if (size >= data.length) {
            growData();
        }
        
        if (index < size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (indexIsOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        
        return (T) data[index];
    }
    
    @Override
    public void set(T value, int index) {
        if (indexIsOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        
        if (index < size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (indexIsOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        
        Object removedObject = data[index];
        
        if (index < size) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        
        size--;
        
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        int indexOfRemoved = 0;
        Object removedObject = null;
        boolean haveFoundElement = false;
        
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], element)) {
                removedObject = data[i];
                indexOfRemoved = i;
                haveFoundElement = true;
            }
        }
        
        if (!haveFoundElement) {
            throw new NoSuchElementException();
        }
        
        System.arraycopy(data, indexOfRemoved + 1, data, indexOfRemoved, size - indexOfRemoved - 1);
        
        size--;
        
        return (T) removedObject;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    private void growData() {
        Object [] biggerData = new Object[data.length + (data.length >> 1)];
        System.arraycopy(data,0,biggerData,0,size);
        data = biggerData;
    }
    
    private boolean indexIsOutOfBounds(int index) {
        return index >= size || index < 0;
    }
}
