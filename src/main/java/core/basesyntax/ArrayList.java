package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VALUE = 10;
    private static final double INCREASE_INDEX = 1.5;
    private int sum;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_VALUE];
    }

    @Override
    public void add(T value) {
        checkArraySize();
        array[sum++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkArraySize();
        checkIndex(index);
        if (index != sum) {
            System.arraycopy(array, index, array, index + 1, sum);
        }
        array[index] = value;
        sum++;
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removeElement = array[index];
        if (index != sum - 1) {
            System.arraycopy(array, index + 1, array, index, sum);
        }
        sum--;
        array[sum] = null;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < sum; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }

    @Override
    public int size() {
        return sum;
    }

    @Override
    public boolean isEmpty() {
        return sum == 0;
    }

    private void checkArraySize() {
        if (sum == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * INCREASE_INDEX)];
            System.arraycopy(array, 0, newArray, 0, sum);
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || ((sum != 0 && sum != 5) && index >= sum)) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
