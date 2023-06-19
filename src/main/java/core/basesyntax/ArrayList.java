package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPICITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[INITIAL_CAPICITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            growElementData();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            add(value);
            return;
        }
        if (index > -1 && index < size) {
            if (size + 1 == elementData.length) {
                growElementData();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index!");
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (index < 0 || index == size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index == size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T elementToReturn = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return elementToReturn;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growElementData() {
        Object[] increasedArr = new Object[(int) (elementData.length * 1.5)];
        System.arraycopy(elementData, 0, increasedArr, 0, elementData.length);
        elementData = increasedArr;
    }
}
