package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int counter = 0;
    private int maxCapacity;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        maxCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (counter == array.length) {
            resize();
        }
        array[counter] = value;
        counter++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > counter) {
            throw new ArrayListIndexOutOfBoundsException("passed index out of bounds.");
        }
        if (counter == array.length) {
            resize();
        }
        for (int i = counter; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        counter++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        int listSize = list.size();
        while (counter + listSize >= maxCapacity) {
            resize();
        }
        for (int i = 0; i < listSize; i++) {
            array[counter] = list.get(i);
            counter++;
        }
    }

    @Override
    public T get(int index) {
        checkIfIndexValid(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexValid(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexValid(index);
        T elementForRemove = array[index];
        if (index == maxCapacity - 1) {
            array[index] = null;
            counter--;
            return elementForRemove;
        }
        for (int i = index; i < counter; i++) {
            array[i] = array[i + 1];
        }
        counter--;
        return elementForRemove;
    }

    @Override
    public T remove(T element) {
        boolean isFound = false;
        int index = 0;
        T elementForRemove = null;
        for (int i = 0; i < counter; i++) {
            if (array[i] != null && array[i].equals(element)
                    || array[i] == null && element == null) {
                isFound = true;
                index = i;
                elementForRemove = array[index];
            }
        }
        if (!isFound) {
            throw new NoSuchElementException("passed element is not found");
        }
        for (int i = index; i < counter; i++) {
            array[i] = array[i + 1];
        }
        counter--;
        return elementForRemove;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    private void resize() {
        int newSize = maxCapacity + maxCapacity / 2;
        T[] tempArray = (T[]) new Object[newSize];
        if (counter >= 0) {
            System.arraycopy(array, 0, tempArray, 0, counter);
        }
        maxCapacity = newSize;
        array = tempArray;
    }

    private void checkIfIndexValid(int index) {
        if (index < 0 || index >= counter) {
            throw new ArrayListIndexOutOfBoundsException("passed index out of bounds.");
        }
    }
}
