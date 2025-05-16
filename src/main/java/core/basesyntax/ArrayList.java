package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList(int capasity) {
        if (capasity < 0) {
            throw new ArrayListIndexOutOfBoundsException("public ArrayList(int capasity) {");
        }
        this.elementData = new Object[capasity];
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("add(T value, int index)");
        }
        if (size == elementData.length) {
            grow();
        }
        if (index < size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("public T get(int index) {");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("public void set(T value, int index) {");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("public void set(T value, int index) {");
        }
        T result = (T)elementData[index];
        size--;
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        return result;
    }

    @Override
    public T remove(T element) {
        boolean isExist = false;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementData[i])) {
                isExist = true;
                index = i;
                break;
            }
        }
        if (!isExist) {
            throw new NoSuchElementException("public T remove(T element) {");
        }
        size--;
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    private void grow() {
        int size = elementData.length == 0 ? DEFAULT_CAPACITY : elementData.length * 2;
        elementData = Arrays.copyOf(elementData, size << 1);
    }
}
