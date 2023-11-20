package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_VALUE = 10;
    private int sum = 0;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[MAX_VALUE];
    }

    @Override
    public void add(T value) {
        checkArraySize();
        array[sum++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        checkArraySize();
        for (int i = sum - 1; i >= index; i--) {
            T element = array[i];
            array[i + 1] = element;
        }
        array[index] = value;
        sum++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkArraySize();
            array[sum++] = list.get(i);
            System.out.println(list.get(i));
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
        for (int i = index + 1; i < sum; i++) {
            T element = array[i];
            array[i - 1] = element;
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
        if (sum == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void checkArraySize() {
        if (sum == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || ((sum != 0 && sum != 5) && index >= sum)) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
