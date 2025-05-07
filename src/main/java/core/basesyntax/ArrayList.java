package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] valueArr;
    private int size;

    public ArrayList() {
        valueArr = new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        resize();
        valueArr[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds ArrayList");
        }
        resize();
        System.arraycopy(valueArr, index, valueArr, index + 1, size - index);
        valueArr[index] = value;
        size++;
    }

    public void resize() {
        if (size == valueArr.length) {
            Object[] elementData = new Object[valueArr.length * 3 / 2];
            System.arraycopy(valueArr, 0, elementData, 0, size);
            valueArr = elementData;
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
        checkIndex(index);
        return (T) valueArr[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        valueArr[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeNumber = (T) valueArr[index];
        System.arraycopy(valueArr, index + 1, valueArr, index, size - index - 1);
        size--;
        return removeNumber;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (valueArr[i] == element || element != null && element.equals(valueArr[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds ArrayList");
        }
    }
}
