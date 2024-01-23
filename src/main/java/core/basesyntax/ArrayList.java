package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static int INITIAL_CAPACITY = 10;
    private int size;
    private T[] values;
    private T[] biggerArray;

    public ArrayList() {
        values = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {

    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {
    }

    @Override
    public T get(int index) {

        return null;
    }
    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {

        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
}
