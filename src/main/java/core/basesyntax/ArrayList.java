package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] tempArray;
    private int size;

    public ArrayList() {
        tempArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        int newLength = (int) (tempArray.length * 1.5);
        T[] newTempArray = (T[]) new Object[newLength];
        System.arraycopy(tempArray,0, newTempArray, 0, size);
        tempArray = newTempArray;
    }

    private void indexCheck(int index) {
        if (index >= 0 && index < size) {
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index. Index should be: 0 <= index < "
            + size);
    }

    @Override
    public void add(T value) {
        if (size == tempArray.length) {
            grow();
        }
        tempArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index. Should be: 0 <= index <= "
                + size);
        }
        if (size == tempArray.length) {
            grow();
        }
        System.arraycopy(tempArray, index, tempArray, (index + 1), (size - index));
        tempArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new NullPointerException("Provided List is empty.");
        }
        while (tempArray.length < (size + list.size())) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            tempArray[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return tempArray[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        if (index == size) {
            tempArray[index] = value;
            size++;
        } else {
            tempArray[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T oldValue = tempArray[index];
        System.arraycopy(tempArray, index + 1, tempArray, index, (size - index - 1));
        tempArray[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < tempArray.length; i++) {
            if (tempArray[i] == element || (tempArray[i] != null && tempArray[i].equals(element))) {
                T oldValue = tempArray[i];
                System.arraycopy(tempArray, i + 1, tempArray, i, (size - i - 1));
                tempArray[--size] = null;
                return oldValue;
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

    @Override
    public String toString() {
        return "ArrayList{"
                + "tempArray="
                + Arrays.toString(tempArray)
                + '}';
    }
}
