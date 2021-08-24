package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEF_CAPACITY = 10;
    private int sizeArray;
    private T[] elements;
    private int arrayCapasity;

    public ArrayList(int sizeArray, T[] elements) {
        this.sizeArray = sizeArray;
        this.elements = elements;
    }

    public ArrayList() {
        arrayCapasity = DEF_CAPACITY;
        elements = (T[]) new Object[arrayCapasity];
    }

    private void grow() {
        arrayCapasity *= 1.5;
        T[] tempArray = (T[]) new Object[arrayCapasity];
        System.arraycopy(elements, 0, tempArray, 0, sizeArray);
        elements = tempArray;
    }

    private void checkItem(int index) {
        if (index < 0 || index > sizeArray) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public void add(T value) {
        if (sizeArray == elements.length) {
            grow();
        }
        add(value, sizeArray);
    }

    @Override
    public void add(T value, int index) {
        checkItem(index);
        if (sizeArray == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, sizeArray - index);
        elements[index] = value;
        sizeArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkItem(index + 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkItem(index + 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkItem(index + 1);
        T removeObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, sizeArray - index - 1);
        sizeArray--;
        return removeObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < sizeArray; i++) {
            if (element == null && elements[i] == null) {
                remove(i);
                return null;
            }
            if (element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element isn't exist");
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeArray == 0;
    }
}
