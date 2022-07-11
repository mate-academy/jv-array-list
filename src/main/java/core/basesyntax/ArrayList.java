package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] objects;
    private int size;


    public ArrayList() {
        objects = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
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


}
