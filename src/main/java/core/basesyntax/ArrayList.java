package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] objectArray = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == objectArray.length) {
            growArray();
        }
        objectArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is greater "
                    + "than ArrayList size or less than 0");
        } else {
            if (size == objectArray.length) {
                growArray();
            }
            for (int i = size; i >= index; i--) {
                if (i >= index + 1) {
                    objectArray[i] = objectArray[i - 1];
                } else {
                    objectArray[i] = null;
                }
            }
            objectArray[index] = value;
        }
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is greater "
                    + "than ArrayList size or less than 0");
        }
        return objectArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is greater than "
                    + "ArrayList size or less than 0");
        }
        objectArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is greater "
                    + "than ArrayList size or less than 0");
        }
        final T object = objectArray[index];
        objectArray[index] = null;
        for (int i = index; i < size; i++) {
            if (i < size - 1) {
                objectArray[i] = objectArray[i + 1];
            }
        }
        size--;
        return object;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((objectArray[i] != null
                    && objectArray[i].equals(element))
                    || (objectArray[i] == null
                    && objectArray[i] == element)) {
                final T object = objectArray[i];
                objectArray[i] = null;
                for (int j = i; j < size; j++) {
                    objectArray[j] = objectArray[j + 1];
                }
                size--;
                return object;
            }
        }
        throw new NoSuchElementException("There is no such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray() {
        T[] objectArrayCopy = (T[]) new Object[objectArray.length + objectArray.length / 2];
        System.arraycopy(objectArray, 0, objectArrayCopy, 0, objectArray.length);
        objectArray = objectArrayCopy;
    }
}
