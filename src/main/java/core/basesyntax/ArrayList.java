package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_MULTIPLICATION_NUMBER = 1.5;
    private static final int START_ARRAY_SIZE = 0;
    private T[] elementsData;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.elementsData = (T[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = START_ARRAY_SIZE;
    }

    @Override
    public void add(T value) {
        if (size() == capacity) {
            double newCapacity = capacity * DEFAULT_MULTIPLICATION_NUMBER;
            this.capacity = (int) newCapacity;
            T[] newElementsData = (T[]) new Object[capacity];
            System.arraycopy(elementsData, 0, newElementsData, 0, size);
            elementsData = newElementsData;
        }
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size() == capacity) {
            double newCapacity = capacity * DEFAULT_MULTIPLICATION_NUMBER;
            this.capacity = (int) newCapacity;
            T[] newElementsData = (T[]) new Object[capacity];
            System.arraycopy(elementsData, 0, newElementsData, 0, size);
            elementsData = newElementsData;
        }
        if (index >= 0 && index <= size) {
            for (int i = elementsData.length; i > 0; i--) {
                elementsData[i] = elementsData[i - 1];
                if (i == index) {
                    elementsData[i] = value;
                    size++;
                    break;
                }
            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index doesn't found");
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            for (int i = 0; i < elementsData.length; i++) {
                if (i == index) {
                    return elementsData[i];
                }
            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index doesn't found");
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            elementsData[index] = value;
        }
        throw new ArrayListIndexOutOfBoundsException("Index doesn't found");
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            for (int i = 0; i < elementsData.length; i++) {
                if (i == index) {
                    for (int j = i; j < elementsData.length; j++) {
                        elementsData[j] = elementsData[j + 1];
                    }
                    size--;
                    break;
                }
            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index doesn't found");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementsData.length; i++) {
            if (elementsData[i] == element) {
                for (int j = i; j < elementsData.length; j++) {
                    elementsData[j] = elementsData[j + 1];
                }
                size--;
                break;
            }
        }
        throw new NoSuchElementException("No such element");
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
