package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPASITY = 10;
    private static final int NOT_FOUND = -1;
    private int capasity;
    private int size;
    private T[] array;

    public ArrayList() {
        capasity = DEFAULT_CAPASITY;
        array = (T[]) new Object[DEFAULT_CAPASITY];
    }

    @Override
    public void add(T value) {
        if (size == capasity) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (invalidIndexToAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of range index");
        }
        if (size == capasity) {
            grow();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
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
        if (invalidIndexToSetGet(index)) {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of range index");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (invalidIndexToSetGet(index)) {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of range index");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = find(element);
        if (index == NOT_FOUND) {
            throw new NoSuchElementException("Element " + element + " was not found");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int oldCapasity = capasity;
        capasity += capasity / 2;
        T[] newArr = (T[]) new Object[capasity];
        if (oldCapasity >= 0) {
            System.arraycopy(array, 0, newArr, 0, oldCapasity);
        }
        array = newArr;
    }

    private int find(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private boolean invalidIndexToAdd(int index) {
        if (index == size) {
            return false;
        }
        return invalidIndexToSetGet(index);
    }

    private boolean invalidIndexToSetGet(int index) {
        return index < 0 || index >= size;
    }
}
