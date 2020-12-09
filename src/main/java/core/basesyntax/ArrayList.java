package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;

    private T[] elementData = (T[]) new Object[INITIAL_LENGTH];
    private int size = 0;

    @Override
    public void add(T value) {
        if (!checkCapacity()) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            if (!checkCapacity()) {
                resize();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return elementData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            T output = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            size--;
            return output;
        }
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == t || (elementData[i] != null && elementData[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkCapacity() {
        return size < elementData.length;
    }

    private void resize() {
        T[] oldData = elementData;
        elementData = (T[]) new Object[getNewCapacity()];
        System.arraycopy(oldData, 0, elementData, 0, size);
    }

    private int getNewCapacity() {
        return elementData.length * 3 / 2 + 1;
    }
}
