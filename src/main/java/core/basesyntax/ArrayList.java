package core.basesyntax;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.elementData = (T[]) Array.newInstance(Object.class, DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            elementData = growIfArrayFull();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index doesn't exist");
        }
        if (size >= elementData.length) {
            elementData = growIfArrayFull();
        }
        //shift of positions to the right side
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
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
            throw new ArrayListIndexOutOfBoundsException("index doesn't exist");
        } else {
            return elementData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index doesn't exist");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index doesn't exist");
        }
        final T value = elementData[index];
        //shift of positions to the left side
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = -1;

        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element)) {
                indexOfElement = i;
                break;
            } else if (elementData[i] == null) {
                indexOfElement = i;
                break;
            }
        }
        if (indexOfElement == -1) {
            throw new NoSuchElementException("No such element");
        }
        //shift of positions to the left side
        for (int i = indexOfElement; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private T[] growIfArrayFull() {
        if (size >= elementData.length) {
            int newSize = (int) (elementData.length * 1.5);
            T[] newArray = (T[]) Array.newInstance(elementData
                    .getClass()
                    .getComponentType(), newSize);
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            this.elementData = newArray;
        }
        return elementData;
    }
}
