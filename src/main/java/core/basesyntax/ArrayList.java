package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] arrayOfObjects;
    private int size;

    public ArrayList() {
        arrayOfObjects = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == arrayOfObjects.length) {
            growCapacity();
        }

        arrayOfObjects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }

        int arrayLength = arrayOfObjects.length;
        Object[] tempo = new Object[arrayLength + 1];
        System.arraycopy(arrayOfObjects, 0, tempo, 0, arrayLength);
        arrayOfObjects = new Object[arrayLength + 1];
        arrayOfObjects[index] = value;
        System.arraycopy(tempo, 0, arrayOfObjects, 0, index);
        System.arraycopy(tempo, index, arrayOfObjects,
                index + 1, size - index);

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
        if (index < size && index >= 0) {
            return (T) arrayOfObjects[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            arrayOfObjects[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    @Override
    public T remove(int index) {
        Object objectByIndex;
        if (index >= 0 && index < size) {
            objectByIndex = arrayOfObjects[index];
            Object[] temp = new Object[size];
            System.arraycopy(arrayOfObjects, 0, temp, 0, size);
            arrayOfObjects = new Object[size - 1];
            System.arraycopy(temp, 0, arrayOfObjects,
                    0, index);
            System.arraycopy(temp, index + 1, arrayOfObjects,
                    index, size - index - 1);
            size--;
            return (T) objectByIndex;
        }
        throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayOfObjects.length; i++) {
            if (arrayOfObjects[i] == element
                    || element != null && element.equals(arrayOfObjects[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCapacity() {
        Object[] tempArray = new Object[size + size / 2];
        System.arraycopy(arrayOfObjects,0,tempArray,0,arrayOfObjects.length);
        arrayOfObjects = new Object[tempArray.length];
        System.arraycopy(tempArray,0,arrayOfObjects,0,tempArray.length);
    }
}
