package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int defoultCapasity = 10;
    private final float growsIndex = 1.5f;
    private T[] array = (T[]) new Object[defoultCapasity];
    private int pointer;

    @Override
    public void add(T value) {
        if (pointer == array.length) {
            grow();
        }
        array[pointer++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > pointer) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Test failed! Can't correctly add element by index - "
                            + index + "Index can be from 0 to " + pointer);
        }
        if (pointer == array.length) {
            grow();
        }
        if (index <= pointer) {
            System.arraycopy(array, index, array, index + 1, pointer - index);
            array[index] = value;
            pointer++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (pointer + list.size() > array.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            if (pointer == array.length) {
                grow();
            }
            array[pointer++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= pointer) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= pointer) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= pointer) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
        T tmp = array[index];
        pointer--;
        System.arraycopy(array, index + 1, array, index, pointer - index);
        return tmp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= pointer; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                T tmp = array[i];
                System.arraycopy(array, i + 1, array, i, pointer - i);
                pointer--;
                return tmp;
            }
        }
        throw new NoSuchElementException("The element - "
                + element + "  was not found in the list");
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    public void grow() {
        int size = Math.round(array.length * growsIndex);
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
