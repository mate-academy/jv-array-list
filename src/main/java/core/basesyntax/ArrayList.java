package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (counter == newCapacity) {
            newCapacity = (int) (array.length * 1.5);
            array = Arrays.copyOf(array, newCapacity);
        }
        array[counter] = value;
        counter++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > counter) {
            throw new ArrayListIndexOutOfBoundsException("index:" + index + "is out of bounds");
        }
        if (counter == newCapacity) {
            newCapacity = (int) (array.length * 1.5);
            array = Arrays.copyOf(array, newCapacity);
        }
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
            if (counter == newCapacity) {
                newCapacity = (int) (array.length * 1.5);
                array = Arrays.copyOf(array, newCapacity);
            }
            array[counter++] = list.get(i);

        }
    }

    @Override
    public T get(int index) {
        if (index >= counter || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index:" + index + "is out of bounds");
        }
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

        if (index >= counter || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index:" + index + "is out of bounds");
        }
        if (counter == newCapacity) {
            newCapacity = (int) (array.length * 1.5);
            array = Arrays.copyOf(array, newCapacity);
        }
        final T removed = array[index];
        System.arraycopy(array, index + 1, array, index, counter - index);
        counter--;
        array[counter] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < counter; i++) {
            if (Objects.equals(element, array[i])) {
                T removedElement = array[i];
                System.arraycopy(array, i + 1, array, i, counter - i);
                array[--counter] = null;
                return removedElement;
            }
        }
        throw new NoSuchElementException("index is out of bounds" + element);
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0 ? true : false;
    }
}
