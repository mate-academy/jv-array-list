package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int NOT_EXISTS = -1;
    private static final int UNIT = 1;
    private T[] array;
    private int capacity;
    private int size;

    public ArrayList() {
        capacity = 10;
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size < capacity) {
            array[size] = value;
            size++;
        } else {
            reallocateArrayMemory();
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            add(value);
        } else if (index < size && index > NOT_EXISTS) {
            size++;
            if (size >= capacity) {
                reallocateArrayMemory();
            }
            for (int i = size; i != index; i--) {
                array[i] = array[i - UNIT];
            }
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Unavailable index for adding element!!!");
        }
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();

        if (size + listSize > capacity) {
            reallocateArrayMemory();
            addAll(list);
            return;
        }

        for (int i = 0; i < listSize; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index > NOT_EXISTS) {
            return array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't get out of bound index!!!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index > NOT_EXISTS) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't set value for "
                    + "non-existent element!!!");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index > NOT_EXISTS) {
            T temporary = array[index];
            for (int i = index; i < size - UNIT; i++) {
                array[i] = array[i + UNIT];
            }
            size--;
            return temporary;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't remove non-existent element!!!");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in ArrayList!!!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void reallocateArrayMemory() {
        T[] temporaryArray = array;

        capacity += (capacity >> UNIT);
        array = (T[]) new Object[capacity];

        for (int i = 0; i < temporaryArray.length; i++) {
            array[i] = temporaryArray[i];
        }
    }
}
