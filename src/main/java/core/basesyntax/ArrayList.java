package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementArray;
    private int size;
    private int capacity;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        elementArray = (T[]) new Object[capacity];
    }

    public T[] elementArrayPlusSize(T[] elementArray, int increasingInSizeByInt) {
        int futureSize = size + increasingInSizeByInt;
        while (futureSize >= capacity) {
            capacity += (capacity >> 1);
        }
        T[] arrayPlusSize = (T[]) new Object[capacity];
        for (int a = 0; a < size; a++) {
            arrayPlusSize[a] = elementArray[a];
        }
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
            int oldNumberOfElement = size - 1;
            for (int a = size - 1; a >= 0; a--) {
                if (a != index) {
                    elementArray[a] = elementArray[oldNumberOfElement - 1];
                    oldNumberOfElement--;
                }
                if (a == index) {
                    elementArray[a] = value;
                    break;
                }
            }
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
            int newNumberOfElement = 0;
            T deletedElement = null;
            for (int a = 0; a < size; a++) {
                if (a != index) {
                    elementArray[newNumberOfElement] = elementArray[a];
                    newNumberOfElement++;
                }
                if (a == index) {
                    deletedElement = elementArray[a];
                }
            }
            elementArray[size - 1] = null;
            size--;
            return deletedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("this index is not correction");
        }
    }

    @Override
    public T remove(T element) {
        int newNumberOfElement = 0;
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
            elementArray[newNumberOfElement] = elementArray[a];
            newNumberOfElement++;
        }
        if (deleteElement == true) {
            for (int a = numberDeletedElement; a < size; a++) {
                elementArray[newNumberOfElement] = elementArray[a + 1];
                newNumberOfElement++;
            }
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