package core.basesyntax;

import java.util.Objects;

public class ArrayList<T> implements List<T> {


    private static final int DEFAULTCAPACITY_EMPTY_ELEMENTDATA = 10;
    private Object[] objects; // should we use transient keyword?
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULTCAPACITY_EMPTY_ELEMENTDATA];
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
        checkIndex(index);
        return (T) objects[index];
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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private void checkIndex (int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index + " Слава Україні!");
        }
    }
}
