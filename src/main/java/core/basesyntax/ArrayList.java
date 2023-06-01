package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementArray;
    private int size;

    public ArrayList() {
        elementArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementArray.length) {
            elementArray = grow(elementArray, 1);
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexEqualsSize(index);
        if (size == elementArray.length) {
            elementArray = grow(elementArray, 1);
        }
        size++;
        System.arraycopy(elementArray, index, elementArray, index + 1, size - index - 1);
        elementArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (size + listSize >= elementArray.length) {
            elementArray = grow(elementArray, listSize);
        }
        for (int a = 0; a < listSize; a++) {
            add(list.get(a));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        size--;
        T deletedElement = elementArray[index];
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index);
        elementArray[size] = null;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        int numberDeletedElement = -1;
        boolean deleteElement = false;
        for (int a = 0; a < size; a++) {
            if (element == elementArray[a] || element != null && element.equals(elementArray[a])) {
                numberDeletedElement = a;
                deleteElement = true;
                break;
            }
        }
        if (deleteElement == true) {
            return remove(numberDeletedElement);
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
        return size == 0;
    }

    private T[] grow(T[] elementArray, int increasingInSizeByInt) {
        int futureSize = size + increasingInSizeByInt;
        int newCapacity = elementArray.length;
        while (futureSize >= newCapacity) {
            newCapacity += (newCapacity >> 1);
        }
        T[] arrayPlusSize = (T[]) new Object[newCapacity];
        System.arraycopy(elementArray, 0, arrayPlusSize, 0, size);
        return arrayPlusSize;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("this index is not correction");
        }
    }

    private void checkIndexEqualsSize(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("this index is not correction");
        }
    }

}
