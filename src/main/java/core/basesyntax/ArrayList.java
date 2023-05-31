package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementArray;
    private int size;
    private int capacity;


    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elementArray = (T[]) new Object[capacity];
    }
    public T[] elementArrayPlusSize(T[] elementArray, int increasingInSizeByInt) {
        int futureSize = size + increasingInSizeByInt;
        while (futureSize >= capacity) {
            capacity += (capacity >> 1);
        }
        T[] arrayPlusSize = (T[]) new Object[capacity];
        System.arraycopy(elementArray, 0, arrayPlusSize, 0, size);
        return arrayPlusSize;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            elementArray = elementArrayPlusSize(elementArray, 1);
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size == capacity) {
                elementArray = elementArrayPlusSize(elementArray, 1);
            }
            size++;
            System.arraycopy(elementArray, index, elementArray, index + 1, size - index - 1);
            elementArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("this index is not correction");
        }
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        int oldSize = size;
        if (size + listSize >= capacity) {
            elementArray = elementArrayPlusSize(elementArray, listSize);
        }
        for (int a = 0; a < listSize; a++) {
            elementArray[oldSize + a] = list.get(a);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return elementArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("this index is not correction");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elementArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("this index is not correction");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T deletedElement = elementArray[index];
            System.arraycopy(elementArray, index + 1, elementArray, index, size - 1 - index);
            elementArray[size - 1] = null;
            size--;
            return deletedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("this index is not correction");
        }
    }

    @Override
    public T remove(T element) {
        int numberDeletedElement = -1;
        T deletedElement = null;
        boolean deleteElement = false;
        for (int a = 0; a < size; a++) {
            if (element == elementArray[a] || element != null && element.equals(elementArray[a])) {
                deletedElement = elementArray[a];
                numberDeletedElement = a;
                deleteElement = true;
                break;
            }
        }
        if (deleteElement == true) {
            System.arraycopy(elementArray, numberDeletedElement + 1, elementArray,
                    numberDeletedElement, size - 1 - numberDeletedElement);
            elementArray[size - 1] = null;
            size--;
            return deletedElement;
        } else {
            throw new NoSuchElementException("this element is missing from the T[] elementArray");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
