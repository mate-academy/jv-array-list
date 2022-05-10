package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] innerData;

    public ArrayList() {
        this.innerData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeArray();
        innerData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("You can't use this index");
        }
        resizeArray();
        System.arraycopy(innerData, index, innerData, index + 1, size - index);
        innerData[index] = value;
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
        checkIndexExist(index);
        return innerData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexExist(index);
        innerData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexExist(index);
        T element = get(index);
        if (index < size - 1) {
            System.arraycopy(innerData, index + 1, innerData, index, size);
        } else {
            innerData[index] = null;
        }
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (innerData[i] == element || (innerData[i] != null && innerData[i].equals(element))) {
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

    private void checkIndexExist(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("You can't use this index" + index);
        }
    }

    private void resizeArray() {
        if (size >= innerData.length) {
            T[] tempArray = (T[]) new Object[innerData.length];
            System.arraycopy(innerData, 0, tempArray, 0, tempArray.length);
            innerData = (T[]) new Object[(int) (innerData.length * 1.5)];
            System.arraycopy(tempArray, 0, innerData, 0, tempArray.length);
        }
    }
}
