package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private Object[] elementData = new Object[SIZE];
    private Object[] bufferedData = new Object[SIZE];
    private int s;
    private static final int START_ITERATOR = 0;
    private static int SIZE = 10;

    public ArrayList() {
        s = START_ITERATOR;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
    public String buffer() {
        return Arrays.toString(bufferedData);
    }
    private void grow() {
        if (s == elementData.length - 1) {
            SIZE *= 1.5;
            bufferedData = new Object[SIZE];
            System.arraycopy(elementData, 0, bufferedData, 0, elementData.length);
            elementData = new Object[SIZE];
            System.arraycopy(bufferedData, 0, elementData, 0, elementData.length);
            bufferedData = new Object[SIZE];
        }
    }

    @Override
    public void add(T value) {
        grow();
        elementData[s] = value;
        s++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > s || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        grow();
        System.arraycopy(elementData, index, bufferedData, 0, elementData.length - index);
        elementData[index] = value;
        System.arraycopy(bufferedData, 0, elementData, index + 1, elementData.length - index - 1);
        s++;
        bufferedData = new Object[SIZE];
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            } else {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > s - 1) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        } try {
            return (T) elementData[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect value");
        }
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index > s - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > s - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        T oldValue = (T)elementData[index];
        try {
            System.arraycopy(elementData, index + 1, elementData, index, elementData.length - index - 1);
            s--;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Index is incorrect");
        }
        return oldValue;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        if (Arrays.asList(elementData).indexOf(element) > s - 1 || Arrays.asList(elementData).indexOf(element) < 0) {
            throw new NoSuchElementException("Index out of range");
        }
        return remove(Arrays.asList(elementData).indexOf(element));
    }

    @Override
    public int size() {
        return s;
    }

    @Override
    public boolean isEmpty() {
        return s == 0;
    }
}
