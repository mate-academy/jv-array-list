package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[10];
    }
    @Override
    public void add(T value) {
        if (!enoughSpace()) {
             grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!correctIndexForAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant add element at such index");
        }
        if (!enoughSpace()) {
            grow();
        }
        if (index == size ) {
            array[size] = value;
        } else {
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = value;
        }
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
        if (!correctIndexForGet(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant get element with such index");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (!correctIndexForAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant set element with such index");
        }
        remove(index);
        add(value, index);
    }

    @Override
    public T remove(int index) {
        if (!correctIndexForGet(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant remove element with at index");
        }
        T removedValue = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i],(element))) {
                return remove(i);
            }
        }
    throw new NoSuchElementException("The element to delete was not found");
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
        array = (T[]) new Object[(int) Math.round(array.length * 1.5)];
    }
    private boolean enoughSpace() {
        return size < array.length;
    }
    private boolean correctIndexForGet(int index) {
        return index < size && index >= 0;
    }
    private boolean correctIndexForAdd(int index) {
        return index <= size && index >= 0;
    }
}
