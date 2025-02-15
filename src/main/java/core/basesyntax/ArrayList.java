package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int newCapacity = INITIAL_CAPACITY;
    private T[] array;
    private int counter = 0;

    public ArrayList() {
        array = (T[]) new Object [INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        createNewCapacity();
        array[counter] = value;
        counter++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > counter) {
            throw new ArrayListIndexOutOfBoundsException("index:" + index + "is out of bounds");
        }
        createNewCapacity();
        if (index < counter) {
            System.arraycopy(array, index, array, index + 1, counter - index);
            array[index] = value;
        } else if (index == counter) {
            array[counter] = value;
        }
        counter++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            createNewCapacity();
            array[counter++] = list.get(i);

        }
    }

    @Override
    public T get(int index) {

        throwAnError(index);

        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= counter || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index:" + index + "is out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {

        throwAnError(index);

        createNewCapacity();

        final T removed = array[index];
        System.arraycopy(array, index + 1, array, index, counter - index);
        counter--;
        array[counter] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < counter; i++) {
                if (array[i] == null) {
                    T removedElement = array[i];
                    System.arraycopy(array, i + 1, array, i, counter - i);
                    array[--counter] = null;
                    return removedElement;
                }
            }
        }
        for (int i = 0; i < counter; i++) {
            if (array[i] != null && array[i].equals(element)) {
                T removedElement = array[i];
                System.arraycopy(array, i + 1, array, i, counter - i);
                array[--counter] = null;
                return removedElement;
            }
        }
        throw new NoSuchElementException("there is no such element present" + element);
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    public void createNewCapacity() {
        if (counter == newCapacity) {
            newCapacity = (int) (array.length * 1.5);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    public void throwAnError(int index) {
        if (index >= counter || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index:" + index + "is out of bounds");
        }
    }

}
