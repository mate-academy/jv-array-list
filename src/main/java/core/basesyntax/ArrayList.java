package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T [] array = (T[]) new  Object [10];
    private int pointer;

    @Override
    public void add(T value) {
        if (pointer == array.length) {
            grow(array);
        }
        array[pointer++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > pointer) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Test failed! Can't correctly add element by index ");
        }
        if (index <= pointer) {
            T[] tmp = (T[]) new Object[array.length - index];
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = array[index + i];
            }
            array[index] = value;
            pointer++;
            if (pointer == array.length) {
                grow(array);
            }
            for (int i = 0; i < tmp.length - 1; i++) {
                array[index + i + 1] = tmp[i];
            }
        }

    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() < array.length - pointer) {
            grow(array);
        }
        for (int i = 0; i < list.size(); i++) {
            if (pointer == array.length) {
                grow(array);
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
        T tmp = null;
        if (index < 0 || index >= pointer) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
        tmp = array[index];
        pointer--;
        for (int i = index; i < pointer; i++) {
            array[i] = array[i + 1];
        }

        return tmp;
    }

    @Override
    public T remove(T element) {
        T tmp = null;
        for (int i = 0; i <= pointer; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                tmp = array[i];
                for (int j = i; j <= pointer; j++) {
                    array[j] = array[j + 1];
                }
                pointer--;
                return tmp;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    public void grow(T[] currentArray) {
        int size = (int)Math.round(currentArray.length * 1.5);
        T[] newArray = (T[]) new  Object[size]; //checkstyle
        System.arraycopy(currentArray, 0, newArray, 0, currentArray.length);
        array = newArray;
    }
}
