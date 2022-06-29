package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        boolean conditionForAdd = index < 0 || index > size;
        if (conditionForAdd) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index
                    + " of bounds for size " + size);
        }
        int s = size;
        if (s == elements.length) {
            grow(size + 1);
        }
        System.arraycopy(elements, index,
                elements, index + 1,
                s - index);
        elements[index] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            T[] tempArray = (T[]) listToArray(list);
            int newCapacity = size + tempArray.length;
            grow(newCapacity);
            System.arraycopy(tempArray, 0, elements, size, tempArray.length);
            size = newCapacity;
        }
    }

    @Override
    public T get(int index) {
        checkIndexInRange(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInRange(index);
        int newSize = size - 1;
        final T oldValue = get(index);
        if (newSize > index) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        size = newSize;
        elements[newSize] = null;
        return oldValue;

    }

    @Override
    public T remove(T element) {
        int index = 0;
        found:
        {
            if (element == null) {
                for (; index < size; index++) {
                    if (elements[index] == null) {
                        remove(index);
                        break found;
                    }
                }
            } else {
                for (; index < size; index++) {
                    if (element.equals(elements[index])) {
                        remove(index);
                        break found;
                    }
                }
                throw new NoSuchElementException();
            }
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = true;
        for (T element : elements) {
            if (element != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public void checkIndexInRange(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index
                    + " of bounds for size " + size);
        }
    }

    public static Object[] listToArray(List<?> list) {
        if (!list.isEmpty()) {
            Object[] tempArray = new Object[list.size()];
            for (int index = 0; index < list.size(); index++) {
                tempArray[index] = list.get(index);
            }
            return tempArray;
        }
        return null;
    }

    private void grow(int minimalCapacity) {
        int oldCapacity = elements.length;
        double multiplicator = 1;
        if (oldCapacity == size) {
            multiplicator = 1.5;
        }
        if (oldCapacity > 0 || isEmpty()) {
            int newCapacity = (int) (oldCapacity * multiplicator + 1);
            T[] tempArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, tempArray, 0, size);
            elements = tempArray;
        } else {
            elements = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minimalCapacity)];
        }
    }
}
