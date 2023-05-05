package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (checkIfMyArrayIsFull()) {
            growFullArray();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (checkIfMyArrayIsFull()) {
            growFullArray();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index);
        T t = null;
        t = elementData[index];
        return t;
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index);
        final T t = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return t;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || (elementData[i] != null
                        && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("non existing element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIfMyArrayIsFull() {
        return size == elementData.length;
    }

    private void growFullArray() {
        T[] grownElementData = (T[]) new Object[(int) (elementData.length * 1.5)];
        System.arraycopy(elementData, 0, grownElementData, 0, elementData.length);
        elementData = grownElementData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("invalid index");
        }
    }
}
